package org.robolectric.shadows;

import android.view.View;
import android.widget.Adapter;
import android.widget.ListView;
import com.android.internal.app.AlertController;
import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;
import org.robolectric.annotation.RealObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.robolectric.Robolectric.directlyOn;

@Implements(AlertController.class)
public class ShadowAlertController {

  @RealObject AlertController realAlertController;

  private CharSequence title;
  private CharSequence message;
  private View view;
  private View customTitleView;
  private int iconId;

  @Implementation
  public void setTitle(CharSequence title) throws InvocationTargetException, IllegalAccessException {
    this.title = title;
    directlyOn(realAlertController, AlertController.class).setTitle(title);
  }

  public CharSequence getTitle() {
    return title == null ? "" : title;
  }

  @Implementation
  public void setCustomTitle(View customTitleView) {
    this.customTitleView = customTitleView;
    directlyOn(realAlertController, AlertController.class).setCustomTitle(customTitleView);
  }

  public View getCustomTitleView() {
    return customTitleView;
  }

  @Implementation
  public void setMessage(CharSequence message) {
    this.message = message;
    directlyOn(realAlertController, AlertController.class).setMessage(message);
  }

  public CharSequence getMessage() {
    return message == null ? "" : message;
  }

  @Implementation
  public void setView(View view) {
    this.view = view;
    directlyOn(realAlertController, AlertController.class).setView(view);
  }

  @Implementation
  public void setIcon(int iconId) {
    this.iconId = iconId;
    directlyOn(realAlertController, AlertController.class).setIcon(iconId);
  }

  public int getIconId() {
    return iconId;
  }

  public View getView() {
    return view;
  }

  public Adapter getAdapter() {
    try {
      Method getListView = realAlertController.getClass().getMethod("getListView");
      ListView listView = (ListView) getListView.invoke(realAlertController);
      return listView.getAdapter();
    } catch (NoSuchMethodException e) {
      throw new RuntimeException(e);
    } catch (InvocationTargetException e) {
      throw new RuntimeException(e);
    } catch (IllegalAccessException e) {
      throw new RuntimeException(e);
    }
  }
}
