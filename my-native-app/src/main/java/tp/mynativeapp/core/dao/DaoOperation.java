package tp.mynativeapp.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import tp.mynativeapp.core.entity.Operation;

public interface DaoOperation extends JpaRepository<Operation,Long> {

}
