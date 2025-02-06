package at.htlleonding.healthchecks;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;

@Readiness
@ApplicationScoped
public class MyReadinessCheck implements HealthCheck {
    boolean healthy = true;

    @Override
    public HealthCheckResponse call() {
        return HealthManipulator.readinessHealth
                ? HealthCheckResponse.up("dummy-readiness-check")
                : HealthCheckResponse.down("dummy-readiness-check");
    }
}
