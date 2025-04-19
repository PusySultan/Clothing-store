package org.example.repository;

import org.example.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Integer>
{
    // TODO any function if need
}
