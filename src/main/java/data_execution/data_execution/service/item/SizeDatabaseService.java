package data_execution.data_execution.service.item;

import data_execution.data_execution.entity.item.Size;
import data_execution.data_execution.repository.item.SizeRepository;
import org.springframework.stereotype.Service;

@Service
public class SizeDatabaseService implements SizeService {
    private final SizeRepository sizeRepository;

    public SizeDatabaseService(SizeRepository sizeRepository) {
        this.sizeRepository = sizeRepository;
    }

    @Override
    public Size create(Size size) {
        return sizeRepository.save(size);
    }
}
