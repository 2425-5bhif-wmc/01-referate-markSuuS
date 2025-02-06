#!/bin/bash

echo "🚀 Starte das Kubernetes Dashboard in LeoCloud..."
leocloud get template dashboard | kubectl apply -f -

echo "⏳ Warte, bis der Dashboard-Pod vollständig bereit ist..."
kubectl wait --for=condition=ready pod -l app=dashboard --timeout=60s
echo "✅ Dashboard-Pod ist bereit!"

echo "🔑 Erstelle Bearer Token für das Dashboard..."
TOKEN=$(kubectl create token if200156)

echo "✅ Öffne diesen URL im Browser: http://localhost:8001/#/workloads?namespace=student-if200156"
echo "🔑 Bearer Token: $TOKEN"

echo "🚀 Starte Port-Forward für das Dashboard..."
kubectl port-forward svc/dashboard 8001:8000 &