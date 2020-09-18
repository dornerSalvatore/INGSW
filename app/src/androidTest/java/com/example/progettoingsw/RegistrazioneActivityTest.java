package com.example.progettoingsw;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class RegistrazioneActivityTest {

    @Rule
    public ActivityTestRule<MainActivityT> mActivityTestRule = new ActivityTestRule<>(MainActivityT.class);

    @Test
    public void registrazioneActivityTest() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.bottone2), withText("Registrati"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.textusr),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));








        appCompatEditText.perform(replaceText("vittoria"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.textpwd),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("giorgio"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.textemail),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText3.perform(replaceText("vittoria@gmail.com"), closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.textnick),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText4.perform(replaceText("vittoriia"), closeSoftKeyboard());

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.textusr), withText("vittoria"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        appCompatEditText5.perform(replaceText("vittoria72"));

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.textusr), withText("vittoria72"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        appCompatEditText6.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText7 = onView(
                allOf(withId(R.id.textnome),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        appCompatEditText7.perform(replaceText("vittoria"), closeSoftKeyboard());

        ViewInteraction appCompatEditText8 = onView(
                allOf(withId(R.id.textcognome),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        appCompatEditText8.perform(replaceText("volpe"), closeSoftKeyboard());

        ViewInteraction appCompatRadioButton = onView(
                allOf(withId(R.id.visualizzaNomeCognome), withText("Si"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                9),
                        isDisplayed()));
        appCompatRadioButton.perform(click());


        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.invia), withText("Registrati"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                8),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction appCompatEditText9 = onView(
                allOf(withId(R.id.textpwd), withText("giorgio"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText9.perform(click());

        ViewInteraction appCompatEditText10 = onView(
                allOf(withId(R.id.textpwd), withText("giorgio"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText10.perform(replaceText("Vittoria72"));

        ViewInteraction appCompatEditText11 = onView(
                allOf(withId(R.id.textpwd), withText("Vittoria72"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText11.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText12 = onView(
                allOf(withId(R.id.textemail), withText("vittoria@gmail.com"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText12.perform(replaceText("vittoria.gmail.com"));

        ViewInteraction appCompatEditText13 = onView(
                allOf(withId(R.id.textemail), withText("vittoria.gmail.com"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText13.perform(closeSoftKeyboard());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.invia), withText("Registrati"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                8),
                        isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction appCompatEditText14 = onView(
                allOf(withId(R.id.textemail), withText("vittoria.gmail.com"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText14.perform(click());

        ViewInteraction appCompatEditText15 = onView(
                allOf(withId(R.id.textemail), withText("vittoria.gmail.com"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText15.perform(replaceText("lulli@gmail.com"));

        ViewInteraction appCompatEditText16 = onView(
                allOf(withId(R.id.textemail), withText("lulli@gmail.com"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText16.perform(closeSoftKeyboard());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.invia), withText("Registrati"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                8),
                        isDisplayed()));
        appCompatButton4.perform(click());

        ViewInteraction appCompatEditText17 = onView(
                allOf(withId(R.id.textemail), withText("lulli@gmail.com"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText17.perform(replaceText("vittoria@gmail.com"));

        ViewInteraction appCompatEditText18 = onView(
                allOf(withId(R.id.textemail), withText("vittoria@gmail.com"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText18.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText19 = onView(
                allOf(withId(R.id.textnick), withText("vittoriia"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText19.perform(click());

        ViewInteraction appCompatEditText20 = onView(
                allOf(withId(R.id.textnick), withText("vittoriia"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText20.perform(click());

        ViewInteraction appCompatEditText21 = onView(
                allOf(withId(R.id.textnick), withText("vittoriia"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText21.perform(click());

        ViewInteraction appCompatEditText22 = onView(
                allOf(withId(R.id.textnick), withText("vittoriia"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText22.perform(click());

        ViewInteraction appCompatEditText23 = onView(
                allOf(withId(R.id.textnick), withText("vittoriia"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText23.perform(replaceText("vittorii"));

        ViewInteraction appCompatEditText24 = onView(
                allOf(withId(R.id.textnick), withText("vittorii"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText24.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText25 = onView(
                allOf(withId(R.id.textnick), withText("vittorii"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText25.perform(click());

        ViewInteraction appCompatEditText26 = onView(
                allOf(withId(R.id.textnick), withText("vittorii"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText26.perform(click());

        ViewInteraction appCompatEditText27 = onView(
                allOf(withId(R.id.textnick), withText("vittorii"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText27.perform(click());

        ViewInteraction appCompatEditText28 = onView(
                allOf(withId(R.id.textnick), withText("vittorii"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText28.perform(replaceText("dorsal"));

        ViewInteraction appCompatEditText29 = onView(
                allOf(withId(R.id.textnick), withText("dorsal"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText29.perform(closeSoftKeyboard());

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.invia), withText("Registrati"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                8),
                        isDisplayed()));
        appCompatButton5.perform(click());

        ViewInteraction appCompatEditText30 = onView(
                allOf(withId(R.id.textnick), withText("dorsal"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText30.perform(replaceText("dorsal"));

        ViewInteraction appCompatEditText31 = onView(
                allOf(withId(R.id.textnick), withText("dorsal"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText31.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText32 = onView(
                allOf(withId(R.id.textusr), withText("vittoria72"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        appCompatEditText32.perform(replaceText("saldor"));

        ViewInteraction appCompatEditText33 = onView(
                allOf(withId(R.id.textusr), withText("saldor"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        appCompatEditText33.perform(closeSoftKeyboard());

        ViewInteraction appCompatButton6 = onView(
                allOf(withId(R.id.invia), withText("Registrati"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                8),
                        isDisplayed()));
        appCompatButton6.perform(click());

        ViewInteraction appCompatEditText34 = onView(
                allOf(withId(R.id.textemail), withText("vittoria@gmail.com"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText34.perform(replaceText("lulli@gmail.com"));

        ViewInteraction appCompatEditText35 = onView(
                allOf(withId(R.id.textemail), withText("lulli@gmail.com"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText35.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText36 = onView(
                allOf(withId(R.id.textnick), withText("dorsal"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText36.perform(replaceText("vittia"));

        ViewInteraction appCompatEditText37 = onView(
                allOf(withId(R.id.textnick), withText("vittia"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText37.perform(closeSoftKeyboard());

        ViewInteraction appCompatButton7 = onView(
                allOf(withId(R.id.invia), withText("Registrati"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                8),
                        isDisplayed()));
        appCompatButton7.perform(click());

        ViewInteraction appCompatEditText38 = onView(
                allOf(withId(R.id.textusr), withText("saldor"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        appCompatEditText38.perform(replaceText("vittori72"));

        ViewInteraction appCompatEditText39 = onView(
                allOf(withId(R.id.textusr), withText("vittori72"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        appCompatEditText39.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText40 = onView(
                allOf(withId(R.id.textnick), withText("vittia"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText40.perform(replaceText("dorsal"));

        ViewInteraction appCompatEditText41 = onView(
                allOf(withId(R.id.textnick), withText("dorsal"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText41.perform(closeSoftKeyboard());

        ViewInteraction appCompatButton8 = onView(
                allOf(withId(R.id.invia), withText("Registrati"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                8),
                        isDisplayed()));
        appCompatButton8.perform(click());

        ViewInteraction appCompatEditText42 = onView(
                allOf(withId(R.id.textnick), withText("dorsal"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText42.perform(replaceText("vittoria"));

        ViewInteraction appCompatEditText43 = onView(
                allOf(withId(R.id.textnick), withText("vittoria"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText43.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText44 = onView(
                allOf(withId(R.id.textusr), withText("vittori72"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        appCompatEditText44.perform(replaceText("saldor"));

        ViewInteraction appCompatEditText45 = onView(
                allOf(withId(R.id.textusr), withText("saldor"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        appCompatEditText45.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText46 = onView(
                allOf(withId(R.id.textemail), withText("lulli@gmail.com"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText46.perform(replaceText("vittoria@gmail.com"));

        ViewInteraction appCompatEditText47 = onView(
                allOf(withId(R.id.textemail), withText("vittoria@gmail.com"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText47.perform(closeSoftKeyboard());

        ViewInteraction appCompatButton9 = onView(
                allOf(withId(R.id.invia), withText("Registrati"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                8),
                        isDisplayed()));
        appCompatButton9.perform(click());

        ViewInteraction appCompatEditText48 = onView(
                allOf(withId(R.id.textusr), withText("saldor"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        appCompatEditText48.perform(replaceText(""));

        ViewInteraction appCompatEditText49 = onView(
                allOf(withId(R.id.textusr),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        appCompatEditText49.perform(closeSoftKeyboard());

        ViewInteraction appCompatButton10 = onView(
                allOf(withId(R.id.invia), withText("Registrati"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                8),
                        isDisplayed()));
        appCompatButton10.perform(click());

        ViewInteraction appCompatEditText50 = onView(
                allOf(withId(R.id.textnick), withText("vittoria"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText50.perform(replaceText(""));

        ViewInteraction appCompatEditText51 = onView(
                allOf(withId(R.id.textnick),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText51.perform(closeSoftKeyboard());

        ViewInteraction appCompatButton11 = onView(
                allOf(withId(R.id.invia), withText("Registrati"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                8),
                        isDisplayed()));
        appCompatButton11.perform(click());

        ViewInteraction appCompatEditText52 = onView(
                allOf(withId(R.id.textnick),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText52.perform(replaceText("vitto"), closeSoftKeyboard());

        ViewInteraction appCompatEditText53 = onView(
                allOf(withId(R.id.textemail), withText("vittoria@gmail.com"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText53.perform(replaceText(""));

        ViewInteraction appCompatEditText54 = onView(
                allOf(withId(R.id.textemail),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText54.perform(closeSoftKeyboard());

        ViewInteraction appCompatButton12 = onView(
                allOf(withId(R.id.invia), withText("Registrati"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                8),
                        isDisplayed()));
        appCompatButton12.perform(click());

        ViewInteraction appCompatEditText55 = onView(
                allOf(withId(R.id.textusr),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        appCompatEditText55.perform(replaceText("vittoria"), closeSoftKeyboard());

        ViewInteraction appCompatEditText56 = onView(
                allOf(withId(R.id.textnick), withText("vitto"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText56.perform(replaceText(""));

        ViewInteraction appCompatEditText57 = onView(
                allOf(withId(R.id.textnick),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText57.perform(closeSoftKeyboard());

        ViewInteraction appCompatButton13 = onView(
                allOf(withId(R.id.invia), withText("Registrati"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                8),
                        isDisplayed()));
        appCompatButton13.perform(click());

        ViewInteraction appCompatEditText58 = onView(
                allOf(withId(R.id.textusr), withText("vittoria"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        appCompatEditText58.perform(replaceText(""));

        ViewInteraction appCompatEditText59 = onView(
                allOf(withId(R.id.textusr),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        appCompatEditText59.perform(closeSoftKeyboard());

        ViewInteraction appCompatButton14 = onView(
                allOf(withId(R.id.invia), withText("Registrati"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                8),
                        isDisplayed()));
        appCompatButton14.perform(click());
        ViewInteraction appCompatEditText63 = onView(
                allOf(withId(R.id.textusr),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("saldor"), closeSoftKeyboard());
        ViewInteraction appCompatEditText64 = onView(
                allOf(withId(R.id.textemail),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText3.perform(replaceText("saldor@gmail.com"), closeSoftKeyboard());
        ViewInteraction appCompatEditText65 = onView(
                allOf(withId(R.id.textnick),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText4.perform(replaceText("dorsal"), closeSoftKeyboard());
        ViewInteraction appCompatButton16 = onView(
                allOf(withId(R.id.invia), withText("Registrati"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                8),
                        isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction appCompatEditText60 = onView(
                allOf(withId(R.id.textusr),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        appCompatEditText60.perform(replaceText("vittoria"), closeSoftKeyboard());

        ViewInteraction appCompatEditText61 = onView(
                allOf(withId(R.id.textemail),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText61.perform(replaceText("vittoria@gmail.com"), closeSoftKeyboard());

        ViewInteraction appCompatEditText62 = onView(
                allOf(withId(R.id.textnick),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText62.perform(replaceText("vitty"), closeSoftKeyboard());

        ViewInteraction appCompatButton15 = onView(
                allOf(withId(R.id.invia), withText("Registrati"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                8),
                        isDisplayed()));
        appCompatButton15.perform(click());

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
