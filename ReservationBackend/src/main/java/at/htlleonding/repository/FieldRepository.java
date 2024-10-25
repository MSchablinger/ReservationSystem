package at.htlleonding.repository;

import at.htlleonding.model.Account;
import at.htlleonding.model.Field;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FieldRepository implements PanacheRepository<Field> {
    public void update(Field field) {
        getEntityManager().merge(field);
    }
}
