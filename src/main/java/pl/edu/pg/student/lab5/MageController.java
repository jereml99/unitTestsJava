package pl.edu.pg.student.lab5;

import java.util.Optional;

public class MageController {
    private MageRepository repository;
    public MageController(MageRepository repository) {
        this.repository = repository;
    }
    public String find(String name) {
            Optional<Mage> answer = repository.find(name);
            if(answer.isPresent())
            {
                return answer.get().toString();
            }
            return "not found";

    }
    public String delete(String name) {
        try {
            repository.delete(name);
            return "done";
        } catch (Exception e) {
            return "not found";
        }
    }
    public String save(String name, String level) {
        try {
            repository.save(new Mage(name,Integer.valueOf(level)));
            return "done";
        } catch (Exception e) {
            return "bad request";
        }
    }
}
