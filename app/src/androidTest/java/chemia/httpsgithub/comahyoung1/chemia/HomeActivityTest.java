package chemia.httpsgithub.comahyoung1.chemia;

import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Aaron on 4/4/2017.
 */

@RunWith(AndroidJUnit4.class)
public class HomeActivityTest {
    @Rule public final ActivityRule<HomePage> main = new ActivityRule<>(HomePage.class);

    @Test
    public void shouldBeAbleToLaunchHomeScreen(){
        onView(withText("Welcome")).check(ViewAssertions.matches(isDisplayed()));
    }
}
