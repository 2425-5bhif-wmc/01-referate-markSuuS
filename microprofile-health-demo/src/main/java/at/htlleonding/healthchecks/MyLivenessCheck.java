package at.htlleonding.healthchecks;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

@Liveness
@ApplicationScoped
public class MyLivenessCheck implements HealthCheck {

    @Override
    public HealthCheckResponse call() {
        return HealthManipulator.livenessHealth
                ? HealthCheckResponse.up("dummy-liveness-check")
                : HealthCheckResponse.down("dummy-liveness-check");
    }
}
