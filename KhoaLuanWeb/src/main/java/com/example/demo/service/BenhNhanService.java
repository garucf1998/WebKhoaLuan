package com.example.demo.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import enity.BenhNhan;
import enity.PhieuKhambenh;
import enity.Role;
import enity.TaiKhoan;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
@Repository
public class BenhNhanService {
	
//	static String GET_ALL_BENH_NHAN="http://13.212.45.136:5001/benhnhan/getall";
//	static String GET_ALL_PHIEU_KHAM="http://13.212.45.136:5001/benhnhan/getlist";
//	static String GET_ONE_BENH_NHAN="http://13.212.45.136:5001/benhnhan/getone";
//	static String PUT_BENH_NHAN="http://13.212.45.136:5001/benhnhan/update";
//	static String GET_BENH_NHAN_THEO_TEN="http://13.212.45.136:5001/benhnhan/getbyname";
//	static String GET_BENH_NHAN_THEO_SDT="http://13.212.45.136:5001/benhnhan/getbysdt";
//	static String GET_BENH_NHAN_THEO_CMND="http://13.212.45.136:5001/benhnhan/getbycmnd";
//	static String POST_BENH_NHAN="http://13.212.45.136:5001/benhnhan/insert";
//	static String GET_ONE_ROLE="http://13.212.45.136:5001/role/getone/5";
	
	static String GET_ALL_BENH_NHAN="http://localhost:5001/benhnhan/getall";
	static String GET_ALL_PHIEU_KHAM="http://localhost:5001/benhnhan/getlist";
	static String GET_ONE_BENH_NHAN="http://localhost:5001/benhnhan/getone";
	static String PUT_BENH_NHAN="http://localhost:5001/benhnhan/update";
	static String GET_BENH_NHAN_THEO_TEN="http://localhost:5001/benhnhan/getbyname";
	static String GET_BENH_NHAN_THEO_SDT="http://localhost:5001/benhnhan/getbysdt";
	static String GET_BENH_NHAN_THEO_CMND="http://localhost:5001/benhnhan/getbycmnd";
	static String POST_BENH_NHAN="http://localhost:5001/benhnhan/insert";
	static String GET_ONE_ROLE="http://localhost:5001/role/getone/5";
	static String GET_BENH_NHAN_BY_LICH_HEN="http://localhost:5001/lichhen/getlichhen";
	static String GET_BENH_NHAN_USERNAME="http://localhost:5001/benhnhan/getbytaikhoan";
	
	/**
	 * @author Vien
	 * date: 12/4/2021
	 * @return list danh s??ch b???nh nh??n
	 * @decription: L???y danh s??nh b??nh nh??n ???????c g???i v??? t??? RestFullAPI
	 * */
	//[START GetAll]
	public  List<BenhNhan>  GetAllBenhNhan() throws IOException {
		List<BenhNhan>getall=new ArrayList<>();
	    URL urlForGetRequest = new URL(GET_ALL_BENH_NHAN);
	    String readLine = null;
	    HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
	    conection.setRequestMethod("GET"); // set userId its a sample here
	    conection.setRequestProperty("Content-Type", "application/json");
	    int responseCode = conection.getResponseCode();

	    if (responseCode == HttpURLConnection.HTTP_OK) {
	        BufferedReader in = new BufferedReader(
	            new InputStreamReader(conection.getInputStream()));
	        String response = new String();
	        while ((readLine = in .readLine()) != null) {
	            response+=(readLine);
	        } in .close();
	        if(responseCode==200)
	        {
	        	Gson gson = new GsonBuilder()
	        		    .setDateFormat("yyyy-MM-dd")
	        		    .create();
		        JsonParser parser = new JsonParser();
		        JsonArray object = (JsonArray) parser.parse(response);// response will be the json String
		        BenhNhan[] benhNhanList = gson.fromJson(object, BenhNhan[].class);
		        	
		        for(int i=0;i<benhNhanList.length;i++)
		        	getall.add(benhNhanList[i]);
	        }
	        else
	        {
	        	return null;
	        }
	        
	    } else {
	        System.out.println("GET NOT WORKED");
	    }
		return getall;

	}
	//[END GetALL]
	
	
	/**
	 * @author Vien
	 * date : 12/4/2021
	 * @return Th??m b???nh nh??n v??o c?? s??? d??? li???u 
	 * @decripstion : Th??m b???nh nh??n b???ng c??i s??? d???ng RestFull API
	 * */
	//[START POST Request]
	public  int POSTBenhNhan(BenhNhan bn) throws IOException {

		Gson gson = new GsonBuilder()
    		    .setDateFormat("yyyy-MM-dd")
    		    .create();
		String POST_PARAMS = gson.toJson(bn);
	    System.out.println(POST_PARAMS);
	    URL obj = new URL(POST_BENH_NHAN);
	    HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
	    postConnection.setRequestMethod("POST");
	    postConnection.setRequestProperty("Content-Type","application/json;charset=utf8");


	    postConnection.setDoOutput(true);
	    OutputStream os = postConnection.getOutputStream();
	    os.write(POST_PARAMS.getBytes());
	    os.flush();
	    os.close();


	    int responseCode = postConnection.getResponseCode();
	    System.out.println("POST Response Code :  " + responseCode);
	    System.out.println("POST Response Message : " + postConnection.getResponseMessage());

	    if (responseCode == HttpURLConnection.HTTP_CREATED) { //success
	        BufferedReader in = new BufferedReader(new InputStreamReader(
	            postConnection.getInputStream()));
	        String inputLine;
	        StringBuffer response = new StringBuffer();

	        while ((inputLine = in .readLine()) != null) {
	            response.append(inputLine);
	        } in .close();

	        // print result
	        System.out.println(response.toString());
	    } else {
	        System.out.println("POST NOT WORKED");
	    }
	    return responseCode;
	}
	//[End POST Request]
	
	/**
	 * @author Vien
	 * date : 12/4/2021
	 * @return C???p nh??t b???nh nh??n v??o c?? s??? d??? li???u 
	 * @decripstion : C???p nh???t b???nh nh??n b???ng c??i s??? d???ng RestFull API
	 * */
	//[START PUT Request]
	public  int PUTBenhNhan(BenhNhan bn) throws IOException {

		Gson gson = new GsonBuilder()
    		    .setDateFormat("yyyy-MM-dd")
    		    .create();
		String PUT_PARAMS = gson.toJson(bn);
	    System.out.println(PUT_PARAMS);
	    URL obj = new URL(PUT_BENH_NHAN+"/"+bn.getId());
	    HttpURLConnection putConnection = (HttpURLConnection) obj.openConnection();
	    putConnection.setRequestMethod("PUT");
	    putConnection.setRequestProperty("Content-Type", "application/json");


	    putConnection.setDoOutput(true);
	    OutputStream os = putConnection.getOutputStream();
	    os.write(PUT_PARAMS.getBytes());
	    os.flush();
	    os.close();


	    int responseCode = putConnection.getResponseCode();
	    String message=putConnection.getResponseMessage();
	    

	    if (responseCode == HttpURLConnection.HTTP_CREATED) { //success
	        BufferedReader in = new BufferedReader(new InputStreamReader(
	            putConnection.getInputStream()));
	        String inputLine;
	        StringBuffer response = new StringBuffer();

	        while ((inputLine = in .readLine()) != null) {
	            response.append(inputLine);
	        } in .close();

	        // print result
	        System.out.println(response.toString());
	    } else {
	        System.out.println("PUT NOT WORKED");
	    }
	    return responseCode;
	}
	public Date doingaytuchuoi(String s) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date=new Date();
        try {
             date = formatter.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
	}
	public String doichuoitungay(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateFormat = formatter.format(date);
		return dateFormat;
	}
	public Role GetOneRole(Long id) throws IOException {
		Role role=new Role();
		URL urlForGetRequest = new URL(GET_ONE_ROLE);
		String readLine = null;
		HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
		conection.setRequestMethod("GET"); // set userId its a sample here
		conection.setRequestProperty("Content-Type", "application/json");
		int responseCode = conection.getResponseCode();


		if (responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(
					new InputStreamReader(conection.getInputStream()));
			String response = new String();
			while ((readLine = in .readLine()) != null) {
				response+=(readLine);
			} in .close();
			
			Gson gson = new Gson();
			role = gson.fromJson(response, Role.class);

			
		} else {
			System.out.println("GET NOT WORKED");
		}

		return role;
	}
	public boolean CheckSdt(String sdt)
	{
		Pattern sdtCheck = Pattern.compile("0[0-9]{9}");
		if(sdtCheck.matcher(sdt).matches())
			return true;
		
			return false;		
	}
	public boolean CheckCmnd(String cmnd )
	{
		Pattern cmndCheck = Pattern.compile("[0-9]{9}");
		if(cmndCheck.matcher(cmnd).matches())
			return true;
		
			return false;		
	}
	public boolean CheckEmail(String email)
	{
		Pattern emailCheck = Pattern.compile("^(.+)@(.+)$");
		if(emailCheck.matcher(email).matches())
			return true;
		
			return false;		
	}
	
	public boolean KiemTraTaiKhoan(TaiKhoan tk)
	{
		TaiKhoanService taiKhoanController=new TaiKhoanService();
		TaiKhoan kiemtra=null;
		try {
			kiemtra = taiKhoanController.GetOneTaiKhoan(tk.getUsername());
			System.out.println(tk.getUsername());
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		if(kiemtra.getUsername()==null)
			return false;
		else
			return true;
	}
	
	/**
	 * @author Vien
	 * date: 17/4/2021
	 * @return list danh s??ch b???nh nh??n theo t??n
	 * @decription: L???y danh s??nh b??nh nh??n theo t??n ???????c g???i v??? t??? RestFullAPI
	 * */
	//[START T??m ki???m theo t??n b???nh nh??n]
	public  List<BenhNhan>  SearchName(String name) throws IOException {
		List<BenhNhan>getall=new ArrayList<>();
	    URL urlForGetRequest = new URL(GET_BENH_NHAN_THEO_TEN+"/"+name);
	    String readLine = null;
	    HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
	    conection.setRequestMethod("GET"); // set userId its a sample here
	    conection.setRequestProperty("Content-Type", "application/json");
	    int responseCode = conection.getResponseCode();

	    if (responseCode == HttpURLConnection.HTTP_OK) {
	        BufferedReader in = new BufferedReader(
	            new InputStreamReader(conection.getInputStream()));
	        String response = new String();
	        while ((readLine = in .readLine()) != null) {
	            response+=(readLine);
	        } in .close();
	        if(responseCode==200)
	        {
	        	Gson gson = new GsonBuilder()
	        		    .setDateFormat("yyyy-MM-dd")
	        		    .create();
		        JsonParser parser = new JsonParser();
		        JsonArray object = (JsonArray) parser.parse(response);// response will be the json String
		        BenhNhan[] benhNhanList = gson.fromJson(object, BenhNhan[].class);
		        	
		        for(int i=0;i<benhNhanList.length;i++)
		        	getall.add(benhNhanList[i]);
	        }
	        else
	        {
	        	return null;
	        }
	        
	    } else {
	        System.out.println("GET NOT WORKED");
	    }
		return getall;

	}
	//[END T??m ki???m theo t??n]
	
	/**
	 * @author Vien
	 * date: 17/4/2021
	 * @return list danh s??ch b???nh nh??n theo s??? ??i???n tho???i
	 * @decription: L???y danh s??nh b??nh nh??n theo s??? ??i???n tho???i ???????c g???i v??? t??? RestFullAPI
	 * */
	//[START T??m ki???m theo sdt b???nh nh??n]
	public  List<BenhNhan>  SearchSDT(String sdt) throws IOException {
		List<BenhNhan>getall=new ArrayList<>();
	    URL urlForGetRequest = new URL(GET_BENH_NHAN_THEO_SDT+"/"+sdt);
	    String readLine = null;
	    HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
	    conection.setRequestMethod("GET"); // set userId its a sample here
	    conection.setRequestProperty("Content-Type", "application/json");
	    int responseCode = conection.getResponseCode();

	    if (responseCode == HttpURLConnection.HTTP_OK) {
	        BufferedReader in = new BufferedReader(
	            new InputStreamReader(conection.getInputStream()));
	        String response = new String();
	        while ((readLine = in .readLine()) != null) {
	            response+=(readLine);
	        } in .close();
	        if(responseCode==200)
	        {
	        	Gson gson = new GsonBuilder()
	        		    .setDateFormat("yyyy-MM-dd")
	        		    .create();
		        JsonParser parser = new JsonParser();
		        JsonArray object = (JsonArray) parser.parse(response);// response will be the json String
		        BenhNhan[] benhNhanList = gson.fromJson(object, BenhNhan[].class);
		        	
		        for(int i=0;i<benhNhanList.length;i++)
		        	getall.add(benhNhanList[i]);
	        }
	        else
	        {
	        	return null;
	        }
	        
	    } else {
	        System.out.println("GET NOT WORKED");
	    }
		return getall;

	}
	//[END T??m ki???m theo s??? ??i???n tho???i]
	
	/**
	 * @author Vien
	 * date: 17/4/2021
	 * @return list danh s??ch b???nh nh??n theo ch???ng minh nh??n d??n
	 * @decription: L???y danh s??nh b??nh nh??n theo ch???ng minh nh??n d??n ???????c g???i v??? t??? RestFullAPI
	 * */
	//[START T??m ki???m theo ch???ng minh nh??n d??n b???nh nh??n]
	public  List<BenhNhan>  SearchCMND(String cmnd) throws IOException {
		List<BenhNhan>getall=new ArrayList<>();
	    URL urlForGetRequest = new URL(GET_BENH_NHAN_THEO_CMND+"/"+cmnd);
	    String readLine = null;
	    HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
	    conection.setRequestMethod("GET"); // set userId its a sample here
	    conection.setRequestProperty("Content-Type", "application/json");
	    int responseCode = conection.getResponseCode();

	    if (responseCode == HttpURLConnection.HTTP_OK) {
	        BufferedReader in = new BufferedReader(
	            new InputStreamReader(conection.getInputStream()));
	        String response = new String();
	        while ((readLine = in .readLine()) != null) {
	            response+=(readLine);
	        } in .close();
	        if(responseCode==200)
	        {
	        	Gson gson = new GsonBuilder()
	        		    .setDateFormat("yyyy-MM-dd")
	        		    .create();
		        JsonParser parser = new JsonParser();
		        JsonArray object = (JsonArray) parser.parse(response);// response will be the json String
		        BenhNhan[] benhNhanList = gson.fromJson(object, BenhNhan[].class);
		        	
		        for(int i=0;i<benhNhanList.length;i++)
		        	getall.add(benhNhanList[i]);
	        }
	        else
	        {
	        	return null;
	        }
	        
	    } else {
	        System.out.println("GET NOT WORKED");
	    }
		return getall;

	}
	//[END T??m ki???m b???nh nh??n theo ch???ng minh nh??n d??n]

	/**
	 * @author Vien
	 * date: 20/4/2021
	 * @return m???t b???nh nh??n
	 * @decription: L???y b??nh nh??n theo id b???nh nh??n ???????c g???i v??? t??? RestFullAPI
	 * */
	//[START l???y v??? 1 b???nh nh??n theo id]
	public BenhNhan GetOneBenhNhan(Long id) throws IOException {
		BenhNhan bn=new BenhNhan();
		URL urlForGetRequest = new URL(GET_ONE_BENH_NHAN+"/"+id);
		String readLine = null;
		HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
		conection.setRequestMethod("GET"); // set userId its a sample here
		conection.setRequestProperty("Content-Type", "application/json");
		int responseCode = conection.getResponseCode();


		if (responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(
					new InputStreamReader(conection.getInputStream()));
			String response = new String();
			while ((readLine = in .readLine()) != null) {
				response+=(readLine);
			} in .close();
			
			Gson gson = new GsonBuilder()
        		    .setDateFormat("yyyy-MM-dd")
        		    .create();
			bn = gson.fromJson(response, BenhNhan.class);

			
		} else {
			System.out.println("GET NOT WORKED");
		}

		return bn;
	}
	//[END l???y v??? 1 b???nh nh??n theo id]
	
	/**
	 * @author Vien
	 * date: 21/4/2021
	 * @return list danh s??ch phi???u kh??m b???nh 
	 * @decription: L???y danh s??ch phi???u kh??m nh??n ???????c g???i v??? t??? RestFullAPI
	 * */
	//[START GetAll phi???u kh??m]
	public  List<PhieuKhambenh>  GetAllPhieuKham(Long id) throws IOException {
		List<PhieuKhambenh>getall=new ArrayList<>();
	    URL urlForGetRequest = new URL(GET_ALL_PHIEU_KHAM+"/"+id);
	    String readLine = null;
	    HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
	    conection.setRequestMethod("GET"); // set userId its a sample here
	    conection.setRequestProperty("Content-Type", "application/json");
	    int responseCode = conection.getResponseCode();

	    if (responseCode == HttpURLConnection.HTTP_OK) {
	        BufferedReader in = new BufferedReader(
	            new InputStreamReader(conection.getInputStream()));
	        String response = new String();
	        while ((readLine = in .readLine()) != null) {
	            response+=(readLine);
	        } in .close();
	        if(responseCode==200)
	        {
	        	Gson gson = new GsonBuilder()
	        		    .setDateFormat("yyyy-MM-dd")
	        		    .create();
		        JsonParser parser = new JsonParser();
		        JsonArray object = (JsonArray) parser.parse(response);// response will be the json String
		        PhieuKhambenh[] phieuKhambenhs = gson.fromJson(object, PhieuKhambenh[].class);
		        	
		        for(int i=0;i<phieuKhambenhs.length;i++)
		        	getall.add(phieuKhambenhs[i]);
	        }
	        else
	        {
	        	return null;
	        }
	        
	    } else {
	        System.out.println("GET NOT WORKED");
	    }
		return getall;

	}
	//[END GetALL phi???u kh??m]
	
	/**
	 * @author Vien
	 * date: 12/4/2021
	 * @return list danh s??ch b???nh nh??n
	 * @decription: L???y danh s??nh b??nh nh??n ???????c g???i v??? t??? RestFullAPI
	 * */
	//[START GetAll]
	public  List<String>  GetBenhNhanByLichHen(String date) throws IOException {
		List<String>getall=new ArrayList<>();
	    URL urlForGetRequest = new URL(GET_BENH_NHAN_BY_LICH_HEN+"/"+date);
	    String readLine = null;
	    HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
	    conection.setRequestMethod("GET"); // set userId its a sample here
	    conection.setRequestProperty("Content-Type", "application/json");
	    int responseCode = conection.getResponseCode();

	    if (responseCode == HttpURLConnection.HTTP_OK) {
	        BufferedReader in = new BufferedReader(
	            new InputStreamReader(conection.getInputStream()));
	        String response = new String();
	        while ((readLine = in .readLine()) != null) {
	            response+=(readLine);
	        } in .close();
	        if(responseCode==200)
	        {
	        	Gson gson = new GsonBuilder()
	        		    .setDateFormat("yyyy-MM-dd")
	        		    .create();
		        JsonParser parser = new JsonParser();
		        JsonArray object = (JsonArray) parser.parse(response);// response will be the json String
		        String[] list = gson.fromJson(object, String[].class);
		        	
		        for(int i=0;i<list.length;i++)
		        	getall.add(list[i]);
	        }
	        else
	        {
	        	return null;
	        }
	        
	    } else {
	        System.out.println("GET NOT WORKED");
	    }
		return getall;

	}
	//[END GetALL]
	
	/**
	 * @author Vien
	 * date: 5/5/2021
	 * @return m???t b???nh nh??n
	 * @decription: L???y b??nh nh??n theo username dang nhap b???nh nh??n ???????c g???i v??? t??? RestFullAPI
	 * */
	//[START l???y v??? 1 b???nh nh??n theo username]
	public BenhNhan GetOneBenhNhanByUser(String taikhoan) throws IOException {
		BenhNhan bn=new BenhNhan();
		URL urlForGetRequest = new URL(GET_BENH_NHAN_USERNAME+"/"+taikhoan);
		String readLine = null;
		HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
		conection.setRequestMethod("GET"); // set userId its a sample here
		conection.setRequestProperty("Content-Type", "application/json");
		int responseCode = conection.getResponseCode();


		if (responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(
					new InputStreamReader(conection.getInputStream()));
			String response = new String();
			while ((readLine = in .readLine()) != null) {
				response+=(readLine);
			} in .close();
			
			Gson gson = new GsonBuilder()
        		    .setDateFormat("yyyy-MM-dd")
        		    .create();
			bn = gson.fromJson(response, BenhNhan.class);

			
		} else {
			System.out.println("GET NOT WORKED");
		}

		return bn;
	}
	//[END l???y v??? 1 b???nh nh??n theo username]
}
