package rs.ac.bg.fon.poljoprivredno_gazdinstvo.mapper;

public interface DtoEntityMapper<T, E> {

	T toDto(E e);

	E toEntity(T t);

}
