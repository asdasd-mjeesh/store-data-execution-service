package data_execution.data_execution.service.item;

import data_execution.data_execution.entity.item.Size;
import data_execution.data_execution.repository.item.SizeRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

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

    @Override
    public Collection<Size> createAll(Collection<Size> sizes) {
        return sizeRepository.saveAll(sizes);
    }

    @Override
    public List<Size> getAll() {
        return sizeRepository.findAll();
    }

    @Override
    public long getSizesCount() {
        return sizeRepository.count();
    }
}
