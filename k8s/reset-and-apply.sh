#!/bin/bash

echo "⚠ Lösche alle bestehenden Kubernetes-Ressourcen..."

# Löscht alle Ressourcen im aktuellen Namespace
kubectl delete deployment --all
kubectl delete service --all
kubectl delete ingress --all
kubectl delete pvc --all
kubectl delete secret --all
kubectl delete pod --all

echo "✅ Alle alten Konfigurationen gelöscht."

echo "🗑️ kubectl config löschen"
rm ~/.kube/config

echo "🔐 Login in der LeoCloud"
leocloud auth login

echo "🔐 Erstelle neue Secrets für private Docker-Registry..."

# Benutzer nach Zugangsdaten fragen
read -p "🔹 Gib deinen Docker-Registry-Benutzernamen ein: " DOCKER_USERNAME
read -s -p "🔹 Gib dein Docker-Registry-Passwort ein: " DOCKER_PASSWORD
echo ""  # Neue Zeile für bessere Lesbarkeit
read -p "🔹 Gib deine E-Mail für die Docker-Registry ein: " DOCKER_EMAIL

# Secret für private Container Registry erstellen
kubectl create secret docker-registry regcred \
  --docker-server=ghcr.io \
  --docker-username="$DOCKER_USERNAME" \
  --docker-password="$DOCKER_PASSWORD" \
  --docker-email="$DOCKER_EMAIL"

echo "✅ Secret wurde erfolgreich erstellt."

# Warten, um sicherzustellen, dass das Secret angewendet wurde
sleep 2

echo "🚀 Wende neue Konfigurationen an..."

# Wendet die neuen YAML-Konfigurationen an
kubectl apply -f postgres.yaml
kubectl apply -f quarkus-app.yaml
kubectl apply -f quarkus-ingress.yaml  # Falls Ingress erlaubt ist

echo "✅ Alle neuen Ressourcen wurden deployed."

# Warten, bis Pods gestartet sind
echo "⏳ Warte auf das Hochfahren der Pods..."
kubectl wait --for=condition=ready pod --all --timeout=60s

echo "🎯 Überprüfe den Status der Deployments:"
kubectl get deployments
kubectl get services
kubectl get ingress

echo "✅ Deployment abgeschlossen! 🎉"
