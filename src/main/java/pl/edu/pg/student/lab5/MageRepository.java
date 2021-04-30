/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.pg.student.lab5;

import java.util.Collection;
import java.util.Optional;

/**
 *
 * @author ledwo
 */
public class MageRepository {

  
    private Collection<Mage> collection;
    
      public MageRepository(Collection<Mage> collection) {
        this.collection = collection;
    }
      
    public Optional<Mage> find(String name) {
        Optional<Mage> answer = Optional.empty();

        for(Mage mage: collection)
        {
            if(mage.getName() == name)
            {
                answer = Optional.of(mage);
            }
        }
        return answer;
    }
    public void delete(String name) throws IllegalArgumentException{
        Optional<Mage> answer = find(name);
        if(answer.isPresent())
        {
            collection.remove(answer.get());
        }
        else
        {
            throw new IllegalArgumentException("There is no "+name+" in repository");
        }
    }
    public void save(Mage mage) throws IllegalArgumentException{
        if(find(mage.getName()).isPresent())
        {
            throw new IllegalArgumentException("There is  "+mage+" already in repository");
        }
        else
        {
            collection.add(mage);
        }
    }

}
