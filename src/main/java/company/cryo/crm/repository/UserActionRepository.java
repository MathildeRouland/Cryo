package company.cryo.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import company.cryo.crm.model.UserAction;

public interface UserActionRepository extends JpaRepository<UserAction, Integer> {

}
