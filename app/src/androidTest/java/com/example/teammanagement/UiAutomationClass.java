package com.example.teammanagement;




import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;
import androidx.test.uiautomator.Until;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


@RunWith(AndroidJUnit4.class)
public class UiAutomationClass {

    private static final String BASIC_SAMPLE_PACKAGE
            = "com.example.teammanagement";

    private static final int LAUNCH_TIMEOUT = 5000;

    private static final String STRING_TO_BE_TYPED = "UiAutomator";

    private UiDevice mDevice;

    @Before
    public void startActivityFromHomeScreen() {
        mDevice = UiDevice.getInstance(getInstrumentation());
        mDevice.pressHome();
        final String launcherPackage = getLauncherPackageName();
        assertThat(launcherPackage, notNullValue());
        mDevice.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), LAUNCH_TIMEOUT);
        Context context = getApplicationContext();
        final Intent intent = context.getPackageManager()
                .getLaunchIntentForPackage(BASIC_SAMPLE_PACKAGE);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);    // Clear out any previous instances
        context.startActivity(intent);

        // Wait for the app to appear
        mDevice.wait(Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)), LAUNCH_TIMEOUT);

    }


    @Test
    public void checkHomeScreen() throws UiObjectNotFoundException {

        UiObject menuBtn = mDevice.findObject(new UiSelector().description("More options"));

            assertTrue(menuBtn.exists());
            menuBtn.click();

        UiObject option_menu_1 = mDevice.findObject(new UiSelector().text("Sort By Name"));
        UiObject option_menu_2 = mDevice.findObject(new UiSelector().text("Sort By WIn"));
        UiObject option_menu_3 = mDevice.findObject(new UiSelector().text("Sort By Lose"));


        assertTrue(option_menu_1.exists());
        assertTrue(option_menu_2.exists());
        assertTrue(option_menu_3.exists());

        option_menu_1.click();

          UiObject ui = mDevice.findObject(new UiSelector().resourceId("com.example.teammanagement:id/item_people").index(2));
        ui.click();
        UiObject teamName =mDevice.findObject(new UiSelector().resourceId("com.example.teammanagement:id/label_TeamName"));

        try {
            assertTrue(teamName.getText().equals("Brooklyn Nets"));
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }

    }


    private String getLauncherPackageName() {
        // Create launcher Intent
        final Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);

        // Use PackageManager to get the launcher package name
        PackageManager pm = getApplicationContext().getPackageManager();
        ResolveInfo resolveInfo = pm.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return resolveInfo.activityInfo.packageName;
    }


    @After
    public void teardown() {
     //   mDevice.ex
    }
}
