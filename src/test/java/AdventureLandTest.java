import com.amit.al.LanguageSelector;
import com.amit.al.MainMenu;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;


@RunWith(PowerMockRunner.class)
@PrepareForTest({LanguageSelector.class,MainMenu.class})
public class AdventureLandTest {

    @Mock
    private LanguageSelector languageSelector;
    @Test
    public void shouldGiveOptionToChooseLanguage() throws Exception {
        PowerMockito.mockStatic(LanguageSelector.class);
        PowerMockito.when(LanguageSelector.getInstance()).thenReturn(languageSelector);
        MainMenu mockMenu = Mockito.mock(MainMenu.class);
        PowerMockito.whenNew(MainMenu.class).withAnyArguments().thenReturn(mockMenu);

//        AdventureLand.main(new String[0]);

//        Mockito.verify(languageSelector).displayLanguageSelection();
//        Mockito.verify(mockMenu).start();
    }
}
