package at.htlleonding.repository;

import at.htlleonding.model.Account;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AccountRepository implements PanacheRepository<Account> {
    public void update(Account account) {
        getEntityManager().merge(account);
    }
}
