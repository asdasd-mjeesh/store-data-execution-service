package data_execution.data_execution.service.factory.size;

import data_execution.data_execution.entity.item.Size;
import data_execution.data_execution.entity.item.SizeEnum;

public interface SizeFactory {
    Size getSizeByEnum(SizeEnum sizeEnum);
}
