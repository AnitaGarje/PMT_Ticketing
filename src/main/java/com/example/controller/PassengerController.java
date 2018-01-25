package com.example.controller;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.model.AddLocation;
import com.example.model.Bus;

import com.example.model.Location;
import com.example.model.Passenger;
import com.example.model.PassengerOtp;
import com.example.model.PassengerTrip;

import com.example.model.User;
import com.example.model.Wallet;
import com.example.model.WalletDebit;
import com.example.service.PassengerService;
import com.example.service.PdfGenaratorUtil;
import com.lowagie.text.DocumentException;


@Controller
public class PassengerController {

	@Autowired
	private PassengerService passengerService;
	
	@Autowired
	PdfGenaratorUtil pdfGenaratorUtil;

	
	String OTP;
	double avlBal=0.0;
	
	double AmtTobeAdded;
 
	@RequestMapping(value = "/passenger", method = RequestMethod.GET)
	public ModelAndView passenger() {
		ModelAndView modelAndView = new ModelAndView("passenger");
		Passenger passenger = new Passenger();
		modelAndView.addObject("passenger", passenger);
		return modelAndView;
	}

	@RequestMapping(value = "/transactionSuccess", method = RequestMethod.GET)
	public ModelAndView TransactionSuccess() {
		ModelAndView modelAndView = new ModelAndView("transactionSuccess");
		//Passenger passenger = new Passenger();
		//modelAndView.addObject("passenger", passenger);
		return modelAndView;
	}
	@RequestMapping(value = "/transactionSuccess", method = RequestMethod.POST)
	public ModelAndView PostTransactionSuccess() {
		ModelAndView modelAndView = new ModelAndView("transactionSuccess");
		//Passenger passenger = new Passenger();
		//modelAndView.addObject("passenger", passenger);
		return modelAndView;
	}
	@RequestMapping(value = "/transactionFailure", method = RequestMethod.GET)
	public ModelAndView TransactionFailure() {
		ModelAndView modelAndView = new ModelAndView("transactionFailure");
		//Passenger passenger = new Passenger();
		//modelAndView.addObject("passenger", passenger);
		return modelAndView;
	}
	
	@RequestMapping(value = "/transactionFailure", method = RequestMethod.POST)
	public ModelAndView PostTransactionFailure() {
		ModelAndView modelAndView = new ModelAndView("transactionFailure");
		//Passenger passenger = new Passenger();
		//modelAndView.addObject("passenger", passenger);
		return modelAndView;
	}
	@RequestMapping(value = "/transactionCancel", method = RequestMethod.GET)
	public ModelAndView TransactionCancel() {
		ModelAndView modelAndView = new ModelAndView("transactionCancel");
		//Passenger passenger = new Passenger();
		//modelAndView.addObject("passenger", passenger);
		return modelAndView;
	}
	
	@RequestMapping(value = "/transactionCancel", method = RequestMethod.POST)
	public ModelAndView PostTransactionCancel() {
		ModelAndView modelAndView = new ModelAndView("transactionCancel");
		//Passenger passenger = new Passenger();
		//modelAndView.addObject("passenger", passenger);
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/passenger", method = RequestMethod.POST)
	public String createNewpassenger(@Valid Passenger passenger, HttpServletRequest request,HttpServletResponse response,BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		Passenger passengerExists = passengerService.findPassengerByPhone(passenger.getPhoneNo());
		if (passengerExists != null) {
			bindingResult
					.rejectValue("phoneNo", "error.passenger",
							"There is already a user registered with the email provided");
		}
		System.out.println("In passenger Post meth:" + passenger.getPhoneNo());
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("passenger");
		} else {
			passengerService.savePassenger(passenger);
			passengerService.setPhoneToCookie(request, response, passenger.getPhoneNo());
			modelAndView.addObject("successMessage", "login successfully");
			modelAndView.addObject("passenger", new Passenger());
			modelAndView.setViewName("passenger");
			
		}
		return "redirect:/passengerOtp";
	}	
	
	@RequestMapping(value = "/passengerOtp", method = RequestMethod.GET)
	public ModelAndView passengerOtp(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		PassengerOtp passengerOtp = new PassengerOtp();
		OTP=passengerService.sendSMS(passengerService.getPhoneFromCookie(request, response).getPhoneNo());
		modelAndView.addObject("passengerOtp", passengerOtp);
		modelAndView.setViewName("passengerOtp");
		return modelAndView;
	}

	@RequestMapping(value = "/passengerOtp", method = RequestMethod.POST)
	public String createNewpassengerOtp(@Valid PassengerOtp passengerOtp, BindingResult bindingResult) {
		
		System.out.println("In createNewpassengerOtp  Post meth:" + passengerOtp.getOtp());
		if (OTP.equals(passengerOtp.getOtp())) {
			
			System.out.println("passengerOtp:" + passengerOtp.getOtp());
			return "redirect:/passengerTrip";
		} else {
			
			 return "passengerOtp";
		}
		
	}

	@RequestMapping(value = "/passengerTrip", method = RequestMethod.GET)
	public ModelAndView passengerTrip() {
		
		List<Location> locationList=passengerService.getAllLocations();
		List<Bus> busList=passengerService.getAllBus();
		ModelAndView modelAndView = new ModelAndView();
		PassengerTrip passengerTrip = new PassengerTrip();
		modelAndView.addObject("passengerTrip", passengerTrip);
		modelAndView.addObject("locationList", locationList);
		modelAndView.addObject("busList", busList);
		modelAndView.setViewName("passengerTrip");
		return modelAndView;
	}
	
	@RequestMapping(value = "/passengerTrips", method = RequestMethod.GET)
	public ModelAndView passengerTrips(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		List<PassengerTrip> TripsByPassenger = passengerService.findPassengerTripByPassenger();
		modelAndView.addObject("TripsByPassenger", TripsByPassenger);
		modelAndView.setViewName("passengerTrips");
		return modelAndView;
	}
	
	
	/*@RequestMapping(value = "/getBusDetails", method = RequestMethod.POST)
	public @ResponseBody getBusDetails(@RequestParam String fromloc,@RequestParam String toloc,@RequestParam String mode) {
		ModelAndView modelAndView = new ModelAndView();
		List<PassengerTrip> TripsByPassenger = passengerService.findPassengerTripByPassenger();
		modelAndView.addObject("TripsByPassenger", TripsByPassenger);
		modelAndView.setViewName("passengerTrips");
		return "asdas";
	}*/

	
	@RequestMapping(value = "/passengerTrip", method = RequestMethod.POST)
	public String createNewpassengerTrip(@Valid PassengerTrip passengerTrip, HttpServletRequest request,HttpServletResponse response, BindingResult bindingResult) {
		System.out.println("In createNewpassengerTrip POST Method");
		passengerTrip.setPassenger(passengerService.getPhoneFromCookie(request, response));
		double tikcetCost=passengerService.getTicketCost(passengerTrip.getFromloc(), passengerTrip.getToloc(), passengerTrip.getNots());
		passengerTrip.setTktamt(tikcetCost);
		passengerService.savePassengerTrip(passengerTrip);
		return "redirect:/passengerPay";
	}

	@RequestMapping(value = "/passengerPay", method = RequestMethod.GET)
	public ModelAndView passengerPay() {
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}

	@RequestMapping(value = "/passengerPay", params = "Pay", method = RequestMethod.POST)
	public String pay() {
		return "redirect:/successfulPayed";
		
	}

	@RequestMapping(value="/passengerPay",params="Add",method=RequestMethod.POST)
    public String add()
    {
        return "redirect:/wallet";
    }

	@RequestMapping(value="/successfulPayed",method=RequestMethod.GET)
     public ModelAndView successfulPayed(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("In createsuccessfulPayed Method");
		double avlBal;
		double tktamt;
		ModelAndView modelAndView = new ModelAndView();

		try {
			System.out.println("Pid:" + passengerService.getPhoneFromCookie(request, response).getPid());
			tktamt = passengerService.getTicketAmt(passengerService.getPhoneFromCookie(request, response)).getTktamt();
			avlBal = passengerService.getAvailBal(passengerService.getPhoneFromCookie(request, response)).getAvalbal();
			avlBal = avlBal - tktamt;
			System.out.println("avlBal is:" + avlBal);
			modelAndView.addObject("availbal", avlBal);
			modelAndView.setViewName("successfulPayed");
			System.out.println("After setting availbal");

		} catch (NullPointerException e) {
			avlBal = 0.0;
			tktamt = 0.0;
			avlBal = avlBal - tktamt;
			System.out.println("avlBal is:" + avlBal);
			modelAndView.addObject("availbal", avlBal);
			modelAndView.setViewName("successfulPayed");
			System.out.println("After setting availbal");

		}

		return modelAndView;
	}

	@RequestMapping(value = "/successfulPayed", params = "Done", method = RequestMethod.POST)
	public String done() {
		return "redirect:/passenger";
	}

	
	
	
	
	@RequestMapping(value = "/wallet", method = RequestMethod.GET)
	public ModelAndView wallet(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("In wallet GET Method");
		ModelAndView modelAndView = new ModelAndView();
		Wallet wallet=new Wallet();
	    avlBal=passengerService.getAvalBalInWallet(request,response);
	    System.out.println("avlBal in wallet GET method:"+avlBal);
	    wallet.setAvalbal(avlBal);
		modelAndView.setViewName("wallet");
		modelAndView.addObject("wallet",wallet);
		return modelAndView;
	}
	
	
	@RequestMapping(value="/wallet" ,method=RequestMethod.POST)
    public ModelAndView debit(@Valid Wallet wallet, BindingResult bindingResult,HttpServletRequest request, HttpServletResponse response,RedirectAttributes redir){
		ModelAndView modelAndView = new ModelAndView();
		System.out.println("In wallet POST method");
		wallet.setAvalbal(avlBal+wallet.getAddamt());
		wallet.setPassenger(passengerService.getPhoneFromCookie(request, response));
		System.out.println("from passed wallet object avlBal:" +wallet.getAvalbal());
		passengerService.saveWallet(wallet);
		modelAndView.addObject("successMessage", "Money has been added successfully");
		modelAndView.addObject("wallet", new Wallet());
		modelAndView.setViewName("wallet");
		return modelAndView ;
      }

	@RequestMapping(value = "/addLocation", method = RequestMethod.GET)
	public ModelAndView addLocation() {
		ModelAndView modelAndView = new ModelAndView("addLocation");
		AddLocation adl = new AddLocation();
		modelAndView.addObject("addLocation", adl);
		return modelAndView;
	}
	
	

	@RequestMapping(value = "/addLocation", method = RequestMethod.POST)
	public ModelAndView createNewRoute(@Valid AddLocation addloc,BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView("addLocation");
		//Passenger passengerExists = passengerService.findPassengerByPhone(passenger.getPhoneNo());
		//if (passengerExists != null) {
			//bindingResult
				//	.rejectValue("phoneNo", "error.passenger",
						//	"There is already a user registered with the email provided");
		//}
		try{
			
		
			System.out.println("In addLocation Post meth:");
			if (bindingResult.hasErrors()) {
				modelAndView.setViewName("addLocation");
			} else {
				passengerService.saveRoute(addloc);
				Location l1=new Location();
				l1.setLoc(addloc.getFromloc());
				passengerService.saveLocation(l1);
				
				Location l2=new Location();
				l2.setLoc(addloc.getToloc());
				passengerService.saveLocation(l2);
				modelAndView.addObject("successMessage", "Route added successfully");
				modelAndView.addObject("addLocation", new AddLocation());
				
			}
		}
		catch(Exception mse)
		{
			modelAndView.addObject("successMessage", "Route already added please enter new locations");
			modelAndView.addObject("addLocation", new AddLocation());
		}
		return modelAndView;
	}	
	
	
	@RequestMapping(value = "/addBus", method = RequestMethod.GET)
	public ModelAndView addBus() {
		ModelAndView modelAndView = new ModelAndView("addBus");
		Bus bus = new Bus();
		modelAndView.addObject("bus", bus);
		return modelAndView;
	}
	
	

	@RequestMapping(value = "/addBus", method = RequestMethod.POST)
	public ModelAndView addNewBus(@Valid Bus bus,BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView("addBus");
		//Passenger passengerExists = passengerService.findPassengerByPhone(passenger.getPhoneNo());
		//if (passengerExists != null) {
			//bindingResult
				//	.rejectValue("phoneNo", "error.passenger",
						//	"There is already a user registered with the email provided");
		//}
		try{
			
		
			System.out.println("In addNewBus Post meth:");
			if (bindingResult.hasErrors()) {
				modelAndView.setViewName("addBus");
			} else {
				passengerService.saveBus(bus);
				modelAndView.addObject("successMessage", "Bus added successfully");
				modelAndView.addObject("bus", new Bus());
				
			}
		}
		catch(Exception mse)
		{
			modelAndView.addObject("successMessage", "Bus already added please enter new Bus");
			modelAndView.addObject("bus", new Bus());
		}
		return modelAndView;
	}	
	
	
	@RequestMapping(value = "/viewTicket/{id}", method = RequestMethod.GET)
	public ModelAndView viewTicket(@PathVariable("id") int id) {
		System.out.println("In viewTicket GET Method ");
		ModelAndView modelAndView = new ModelAndView("viewTicket");
		PassengerTrip  passengerTrip=passengerService.findOneTrip(id);
		System.out.println(passengerTrip.toString());
		///TicketPdf tpdf=new TicketPdf();
		//tpdf.main1(null);
		
		 Map<String,String> data = new HashMap<String,String>();
		    data.put("name","James");
		    try {
				pdfGenaratorUtil.createPdf("viewTicket",data);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	
		return modelAndView;
	}
}
	
	
