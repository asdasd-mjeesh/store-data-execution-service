package data_execution.data_execution.service.factory.size;

import data_execution.data_execution.persistance.entity.item.Size;
import data_execution.data_execution.persistance.entity.item.SizeEnum;
import data_execution.data_execution.persistance.entity.item.SizeType;
import data_execution.data_execution.service.factory.ContextInitService;
import data_execution.data_execution.service.factory.EntityContextSynchronizer;
import data_execution.data_execution.service.item.SizeService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SizeEntityFactory implements ContextInitService, EntityContextSynchronizer, SizeFactory {
    private final SizeService sizeService;
    private Set<Size> sizes;

    public SizeEntityFactory(SizeService sizeService) {
        this.sizeService = sizeService;
    }

    @Override
    public void init() {
        if (sizeService.getSizesCount() != SizeEnum.values().length) {
            uploadToDatabase();
        }
        loadFromDatabase();
    }

    @Override
    public void loadFromDatabase() {
        sizes = new HashSet<>(sizeService.getAll());
    }

    protected Set<Size> getSizesByType(SizeEnum[] sizeEnums, SizeType targetType) {
        return Arrays.stream(sizeEnums)
                .filter(sizeEnum -> sizeEnum.getType().equals(targetType))
                .map(Size::new)
                .collect(Collectors.toSet());
    }

    @Override
    public void uploadToDatabase() {
        SizeEnum[] sizeEnums = SizeEnum.values();
        sizeService.createAll(getSizesByType(sizeEnums, SizeType.CLOTHES));
        sizeService.createAll(getSizesByType(sizeEnums, SizeType.SHOES));
    }

    @Override
    public Size getSizeByEnum(SizeEnum sizeEnum) {
        return sizes.stream()
                .filter(size -> size.getName().equals(sizeEnum))
                .findAny().get();
    }
}
