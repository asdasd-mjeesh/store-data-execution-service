package data_execution.data_execution.service.item;

import data_execution.data_execution.entity.item.Size;

import java.util.Collection;
import java.util.List;

public interface SizeService {
    Size create(Size size);
    Collection<Size> createAll(Collection<Size> sizes);
    List<Size> getAll();
    long getSizesCount();
}
