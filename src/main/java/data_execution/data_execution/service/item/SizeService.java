package data_execution.data_execution.service.item;

import data_execution.data_execution.entity.item.Size;

import java.util.List;

public interface SizeService {
    Size create(Size size);
    List<Size> getAll();
    long getSizesCount();
}
