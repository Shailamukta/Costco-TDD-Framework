package pages;

import org.testng.annotations.Test;

import baseUtil.BaseClass;

public class SearchPageTest extends BaseClass {

	@Test
	public void search() {
		sp.searchButton();
	}

}
