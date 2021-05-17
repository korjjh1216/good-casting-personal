
package shop.goodcasting.api.common.service;


import java.util.List;
import java.util.Optional;

public abstract class AbstractService<T> {
    public abstract Long save(T t);
    public abstract Optional<T> findById(Long id);
    public abstract List<T> findAll();
    public abstract Long count();
    public abstract Optional<T> getOne(Long id);
    public abstract Long delete(T t);
    public abstract Boolean existById(Long id);
}
