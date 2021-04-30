/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.pg.student.lab5;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 *
 * @author ledwo
 */
public class MageRepositoryTest {
    
    @Test
    public void delete_noExistingElement_IllegalArgumentException()
    {
       List<Mage> mages = new ArrayList<>();
       mages.add(new Mage("jeremi",100));
       MageRepository magRepository = new MageRepository(mages);


        assertThatThrownBy( () -> {
        magRepository.delete("Andrzej");
       }).isInstanceOf(IllegalArgumentException.class);
       //assertTrue(exception.getClass() == IllegalArgumentException.class);
    }

    @Test
    public void find_noExistingElement_nullOptional()
    {
        List<Mage> mages = new ArrayList<>();
        mages.add(new Mage("jeremi",100));
        MageRepository magRepository = new MageRepository(mages);

        Optional<Mage> optional = magRepository.find("Martyna");

        assertThat(optional).isEmpty();
    }

    @Test
    public void find_existingElement_ElementFromRepo()
    {
        List<Mage> mages = new ArrayList<>();
        mages.add(new Mage("jeremi",100));
        MageRepository magRepository = new MageRepository(mages);

        Optional<Mage> optional = magRepository.find("jeremi");

        assertThat(optional).isPresent();
        assertThat(optional.get().getName()).isEqualTo("jeremi");
    }

    @Test
    public void save_exsistingElement_IllegalArgumentException()
    {
        List<Mage> mages = new ArrayList<>();
        mages.add(new Mage("jeremi",100));
        MageRepository magRepository = new MageRepository(mages);

        assertThatThrownBy( () -> {
            magRepository.save(new Mage("jeremi",120));
        }).isInstanceOf(IllegalArgumentException.class);
    }


}
