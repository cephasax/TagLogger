package br.ufrn.imd.ppgsw.sd.main;

import java.util.concurrent.TimeUnit;

import com.google.gson.Gson;

import br.ufrn.imd.ppgsw.sd.domain.TempTag;
import br.ufrn.imd.ppgsw.sd.domain.User;
import br.ufrn.imd.ppgsw.sd.utils.Converter;

public class MainMock {

	public static void main(String[] args) throws Exception{

			/*
			 * The class ApiReaderNesslab to be instantiated, a new connection
			 * with the Nesslab is opened.
			 */
			
			while (true) {
				String tag = ("{antenna: \"1\", tag: \"3000000000000000000000007547\"}");
				System.out.println(tag);
				
				TempTag tt = new TempTag();
				Gson gson = new Gson();
				tt = gson.fromJson(tag, TempTag.class);
				System.out.println("Tag: " + tt.getTag());
				System.out.println("Antenna: " + tt.getAntenna());
				
				User u = new User();
				u = Converter.tempTagToUser(tt);
				sendToNodeRed(u);
				TimeUnit.SECONDS.sleep(5);
			}
		}

	public static void sendToNodeRed(User user) throws Exception {
		Comunicacao com = new Comunicacao("http://localhost:1880/nodered");

		Gson gson = new Gson();
		String json = gson.toJson(user).toString();

		com.sendPost(json);

	}

}
