package com.example.gradlecassandraparseurl;

import com.example.gradlecassandraparseurl.service.ParseUrlService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GradlecassandraparseurlApplicationTests {
	private static final String result = "Рекламные ссылки: \n" +
			"    http://www.etsy.com/shop/delainederry?ref=l2-shopheader-name\n" +
			"    https://nakedartusa.com/collections/all/delaine-derry-green\n" +
			"    https://twitter.com/HumanPanther\n" +
			"    https://www.facebook.com/groups/32561454731/\n" +
			"    https://www.pinterest.com/humanpanther/\n" +
			"    https://www.instagram.com/humanpanther/\n" +
			"    https://www.etsy.com/listing/533160008/not-my-small-diary-19-unexplained-events?ref=listing-shop-header-0\n" +
			"    https://www.etsy.com/listing/533160008/not-my-small-diary-19-unexplained-events?ref=listing-shop-header-0\n" +
			"    https://www.etsy.com/listing/533160008/not-my-small-diary-19-unexplained-events?ref=listing-shop-header-0\n" +
			"    http://www.king-cat.net/\n" +
			"    http://lizprincepower.com/\n" +
			"    https://davidlasky.carbonmade.com/\n" +
			"    https://www.squattypotty.com/?gclid=CKed39OAi9MCFVUvgQod0LMCtg";
	private static final String inUrl = "http://mysmallwebpage.com/";

	@Autowired
	private ParseUrlService parseUrlService;

	@Autowired
	private MockMvc mvc;

	@Test
	public void testReq() throws Exception {
		this.mvc.perform(post("/parseUrl").param("url", inUrl))
				.andExpect(status().isOk())
				.andExpect(content().string(result));
	}
	@Test
	public void testMeth() throws IOException {
		Assert.assertEquals(result, parseUrlService.parseForAdvertisements(inUrl));
	}

}
