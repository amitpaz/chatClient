package Director;

import presenter.MainPresenter;
import view.Mainview;

public class Director {
	static MainPresenter mainPresnter;
	public static void doInit()
	{
		initMain();
	}

	public static void initMain()
	{
		if(mainPresnter==null)
		{
			mainPresnter = new MainPresenter();
			mainPresnter.addView("main", new Mainview(mainPresnter));
			mainPresnter.ConnectToServer();
			mainPresnter.showView();
			mainPresnter.initAsyncReader();
		}
	}
}
