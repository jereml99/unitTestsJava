package pl.edu.pg.student.lab5;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class MageControllerTest {
    @Test
    public void delete_existingItem_done()
    {
        MageRepository mageRepository = mock(MageRepository.class);
        doNothing().when(mageRepository).delete(anyString());
        MageController mageController = new MageController(mageRepository);

        String answer = mageController.delete("Jeremi");

        Assertions.assertThat(answer).isEqualTo("done");
    }
    @Test
    public void delete_noExistingItem_notFound()
    {
        MageRepository mageRepository = mock(MageRepository.class);
        doThrow(IllegalArgumentException.class).when(mageRepository).delete(anyString());
        MageController mageController = new MageController(mageRepository);

        String answer = mageController.delete("Jeremi");

        Assertions.assertThat(answer).isEqualTo("not found");
    }
    @Test
    public void find_noExistingItem_notFound()
    {
        MageRepository mageRepository = mock(MageRepository.class);
        when(mageRepository.find(anyString())).thenReturn(Optional.empty());
        MageController mageController = new MageController(mageRepository);

        String answer = mageController.find("Jeremi");

        Assertions.assertThat(answer).isEqualTo("not found");
    }

    @Test
    public void find_valid_MageToString()
    {
        MageRepository mageRepository = mock(MageRepository.class);
        when(mageRepository.find(anyString())).thenReturn(Optional.of(new Mage("Jeremi",123)));
        MageController mageController = new MageController(mageRepository);

        String answer = mageController.find("Jeremi");

        Assertions.assertThat(answer).isEqualTo("Mage{name='Jeremi', level=123}");
    }

    @Test
    public void save_valid_done(){
        MageRepository repository = Mockito.mock(MageRepository.class);
        MageController controller = new MageController(repository);
        doNothing().when(repository).save(any());

        String result = controller.save("Jereml", "123");

        ArgumentCaptor<Mage>  argumentCaptor = ArgumentCaptor.forClass(Mage.class);
        verify(repository, times(1)).save(argumentCaptor.capture());
        Mage mageCaptor = argumentCaptor.getValue();
        assertThat(mageCaptor.getName()).isEqualTo("Jereml");
        assertThat(mageCaptor.getLevel()).isEqualTo(123);
        assertThat(result).isEqualTo("done");
    }

    @Test
    public void save_sameKey_badRequest(){
        MageRepository repository = Mockito.mock(MageRepository.class);
        MageController controller = new MageController(repository);
        doThrow(IllegalArgumentException.class).when(repository).save(any());

        String result = controller.save("Jereml", "123");

        assertThat(result).isEqualTo("bad request");
    }
}
