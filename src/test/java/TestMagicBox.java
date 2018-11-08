import com.storm.config.AppConfig;
import com.storm.proj.MagicBox;
import com.storm.work.UseMagicBox;
import com.storm.work.impl.MagicBoxImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.powermock.api.mockito.PowerMockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({UseMagicBox.class, MagicBox.class, Date.class})
//@PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)  // Можно использовать плюшки Spring в тесте
//@ContextConfiguration(classes = {AppConfig.class})   // Без контекста тест со SpringRunner не запустится
public class TestMagicBox {

    //Мокаем до создания экземпляра тестируемого класса UseMagicBox
    @Mock
    MagicBox magicBox;

    @InjectMocks
    UseMagicBox useMagicBox;

    private static final String MAGIC_WORD = "ABRACADABRA";

    @Before
    public void setUp() throws Exception {
        // Статические методы перехватываем до создания экземпляра UseMagicBox
        mockStatic(Date.class);
        when(Date.parse(anyString())).thenReturn(9L);
        useMagicBox = new UseMagicBox();
        initMocks(this); // Без инициализации не заменяются поля с Autowired
        //Мокаем после создания UseMagicBox
        when(magicBox.magicWords()).thenReturn(MAGIC_WORD);
    }

    @Test
    public void testDate() {
        long testDate = useMagicBox.whatTheDate();

        assertEquals(9L, testDate);
    }

    @Test
    public void testMagicWord() {
        String test_word = useMagicBox.doMagic();

        assertEquals(MAGIC_WORD, test_word);
    }
}
