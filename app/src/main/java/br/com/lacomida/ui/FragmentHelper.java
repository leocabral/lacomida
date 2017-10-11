package br.com.lacomida.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import br.com.lacomida.R;

public class FragmentHelper {

	public void add(int containerId, FragmentManager fm, Context context, Class<?> fragmentClass,
	                Bundle extras, boolean addToBackStack) {
		FragmentTransaction transaction = fm.beginTransaction();

		transaction.setCustomAnimations(R.anim.show_from_right, R.anim.exit_to_left,
				R.anim.show_from_left, R.anim.exit_to_right);

		transaction.add(containerId, Fragment.instantiate(context, fragmentClass.getName(), extras));

		if (addToBackStack) {
			transaction.addToBackStack(null);
		}

		transaction.commit();
	}

	public void clearBackStack(FragmentManager fragmentManager) {
		fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
	}
}
