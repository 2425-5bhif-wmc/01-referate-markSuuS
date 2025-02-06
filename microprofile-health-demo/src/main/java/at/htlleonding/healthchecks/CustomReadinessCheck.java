package at.htlleonding.healthchecks;

import io.quarkus.arc.profile.IfBuildProfile;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;

import java.util.Random;

@Readiness
@ApplicationScoped
@IfBuildProfile("dev")
public class CustomReadinessCheck implements HealthCheck {
    @Override
    public HealthCheckResponse call() {
        boolean b = new Random().nextBoolean();

        if(b) {
            return HealthCheckResponse.up("custom readiness check");
        } else {
            return HealthCheckResponse.down("custom readiness check");
        }
    }
}
