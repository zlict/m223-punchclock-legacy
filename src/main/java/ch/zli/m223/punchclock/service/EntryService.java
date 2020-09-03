package ch.zli.m223.punchclock.service;

import ch.zli.m223.punchclock.domain.Entry;
import ch.zli.m223.punchclock.exceptions.EntryNotFoundException;
import ch.zli.m223.punchclock.repository.EntryRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntryService {
    private EntryRepository entryRepository;

    public EntryService(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    public Entry createEntry(Entry entry) {
        return entryRepository.saveAndFlush(entry);
    }

    public Entry updateEntry(Entry entry) {
        if(entryRepository.findById(entry.getId()).isPresent()) {
            return entryRepository.saveAndFlush(entry);
        }
        throw new EntryNotFoundException();
    }

    public List<Entry> findAll() {
        return entryRepository.findAll();
    }

    public void deleteEntry(Long id) {
        try {
            entryRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new EntryNotFoundException();
        }
    }
}
