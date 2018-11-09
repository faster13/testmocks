import com.storm.proj.MagicBox;
import com.storm.work.UseMagicBox;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import java.util.Date;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
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
    @Spy
    ScheduledThreadPoolExecutor service = new ScheduledThreadPoolExecutor(1);

    @InjectMocks
    UseMagicBox useMagicBox;

    private static final String MAGIC_WORD = "ABRACADABRA";

    @Before
    public void setUp() throws Exception {
        // Статические методы перехватываем до создания экземпляра UseMagicBox
        mockStatic(Date.class);
        when(Date.parse(anyString())).thenReturn(9L);
        //service = spy(ScheduledThreadPoolExecutor.class);
        spy(service);
        useMagicBox = new UseMagicBox();
        initMocks(this); // Без инициализации не заменяются поля с Autowired
        //Мокаем после создания UseMagicBox
        when(magicBox.magicWords()).thenReturn(MAGIC_WORD);
        whenNew(ScheduledThreadPoolExecutor.class).withAnyArguments().thenReturn(service);
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

    @Test
    public void testThread() {
        /*when(service.scheduleAtFixedRate(
                any(Runnable.class),
                eq(5L),
                eq(5L),
                eq(TimeUnit.SECONDS)
        )).thenCallRealMethod();*/

        useMagicBox.anyMethod();

        verify(service, atLeastOnce()).scheduleAtFixedRate(
                any(Runnable.class),
                eq(1L),
                eq(1L),
                eq(TimeUnit.MICROSECONDS));
    }
}
