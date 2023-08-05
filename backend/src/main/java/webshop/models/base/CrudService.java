package webshop.models.base;



import webshop.exceptions.AppException;

import java.io.Serializable;
import java.util.List;

public interface CrudService <ID extends Serializable>{
    <T>List<T> findAll(Class<T> resultDtoClass) throws AppException;
    <T> T findById(ID id,Class<T> resultDtoClass) throws AppException;
    <T,U> T insert(U object,Class<T> resultDtoClass)throws AppException;
    <T,U> T update(ID id,U object,Class<T> resultDtoClass) throws AppException;
    void delete(ID id) throws AppException;
}