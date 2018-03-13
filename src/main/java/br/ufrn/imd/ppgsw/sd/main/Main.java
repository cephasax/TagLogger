package br.ufrn.imd.ppgsw.sd.main;
import java.io.IOException;
import java.net.UnknownHostException;

import com.google.gson.Gson;

import api.reader.nesslab.commands.CloseConnection;
import api.reader.nesslab.commands.ReaderTags;
import api.reader.nesslab.commands.ReaderTagsReset;
import api.reader.nesslab.exceptions.SessionFullException;
import api.reader.nesslab.facade.ApiReaderNesslab;
import api.reader.nesslab.interfaces.ApiReaderFacade;
import api.reader.nesslab.utils.OperationUtil;
import br.ufrn.imd.ppgsw.sd.domain.TempTag;
import br.ufrn.imd.ppgsw.sd.domain.User;
import br.ufrn.imd.ppgsw.sd.utils.Converter;

public class Main {

	public static void main(String[] args) throws Exception{
		try {

			/*
			 * The class ApiReaderNesslab to be instantiated, a new connection
			 * with the Nesslab is opened.
			 */
			
			ApiReaderFacade api = new ApiReaderNesslab("192.168.1.231");
			api.defaultConfiguration();
			api.clearTemporaryMemory(50);// Clean memory of 2 in 2 minutes.

			api.executeAction(new ReaderTags());

			while (api.hasResponse()) {
				try {
					api.captureTagsObject();
					if (api.hasNewTag()) {
						String tag = api.getTagUniqueJsonRepresentation();
						System.out.println(tag);
						
						TempTag tt = new TempTag();
						Gson gson = new Gson();
						tt = gson.fromJson(tag, TempTag.class);
						System.out.println("Tag: " + tt.getTag());
						System.out.println("Antenna: " + tt.getAntenna());
						
						User u = new User();
						u = Converter.tempTagToUser(tt);
						
						sendToNodeRed(u);
					}

				} catch (SessionFullException e) {
					api.executeAction(new ReaderTagsReset());
				}
			}

			api.executeAction(new CloseConnection());
		} catch (UnknownHostException e) {
			System.err.println("Host not found: " + OperationUtil.getIpReaderNesslab());
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Don't possible the conection: " + OperationUtil.getIpReaderNesslab());
			System.exit(1);
		}
		}
		
	public static void sendToNodeRed(User user) throws Exception{
		Comunicacao com = new Comunicacao("http://localhost:1880/nodered");
			
		Gson gson = new Gson();
		String json = gson.toJson(user).toString();
		
		com.sendPost(json);
			
	}
			  
	}
	
//}


	