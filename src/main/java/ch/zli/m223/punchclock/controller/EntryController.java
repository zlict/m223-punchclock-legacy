package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.domain.Entry;
import ch.zli.m223.punchclock.service.EntryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/entries")
public class EntryController {
    private EntryService entryService;

    public EntryController(EntryService entryService) {
        this.entryService = entryService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Entry> getAllEntries() {
        return entryService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Entry createEntry(@Valid @RequestBody Entry entry) {
        return entryService.createEntry(entry);
    }

    @RequestMapping("/{id}")
    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteEntry(@PathVariable long id){
        entryService.deleteEntry(id);
    }

    @PutMapping("/{id}")
    public Entry updatEntry(@PathVariable long id, @Valid @RequestBody Entry newEntry){
        return entryService.findById(id).map(entry1 -> {
            entry1.setCheckIn(newEntry.getCheckIn());
            entry1.setCheckOut(newEntry.getCheckOut());
            entry1.setCategory(newEntry.getCategory());
            return entryService.updateEntry(entry1);
        })
        .orElseGet(() -> {
            newEntry.setId(id);
            return entryService.createEntry(newEntry);
        });
    }
}
