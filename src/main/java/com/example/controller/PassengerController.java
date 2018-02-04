package com.example.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.model.AddLocation;
import com.example.model.Bus;
import com.example.model.FromLocation;
import com.example.model.Location;
import com.example.model.Passenger;
import com.example.model.PassengerOtp;
import com.example.model.PassengerTrip;
import com.example.model.ToLocation;
import com.example.model.Wallet;
import com.example.service.PassengerService;



@Controller
public class PassengerController {

	@Autowired
	private PassengerService passengerService;
	
	
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
	
	
	@RequestMapping(value = "/transactionSuccessRir", method = RequestMethod.GET)
	public String transactionSuccessRir() {
		System.out.println("In transactionSuccessRir");;
		return "redirect:/transactionSuccess/50";
	}

	/*@RequestMapping(value = "/transactionSuccess/50", method = RequestMethod.GET)
	public ModelAndView TransactionSuccess() {
		
	}*/
	@RequestMapping(value = "/transactionSuccess/50", method = RequestMethod.POST)
	public ModelAndView PostTransactionSuccess(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("In PostTransactionSuccess meth");
	
		ModelAndView modelAndView = new ModelAndView("transactionSuccess");
		Wallet wallet=new Wallet();
		 Passenger passenger=passengerService.getPhoneFromCookie(request, response);
		System.out.println("passenger.getPhoneNo()): "+passenger.getPhoneNo());
		
		 double setAvalbal=0;
		 
		 if(passengerService.isWalletExist(passenger)==true)
		 {	
			 System.out.println("In TransactionSuccess IF condition");
			 Wallet originalWallet=passengerService.findWalletByPassenger(passenger);
			 wallet.setPassenger(originalWallet.getPassenger());
			 wallet.setWalletid(originalWallet.getWalletid());
			 setAvalbal=originalWallet.getAvalbal()+10;
			 wallet.setAvalbal(setAvalbal);
			 passengerService.updateWallet(wallet);
		 }
		 else{
			 
			 System.out.println("In TransactionSuccess ELSE block");
			 wallet.setPassenger(passenger);
			 setAvalbal=10;
			 wallet.setAvalbal(setAvalbal);
			 passengerService.saveWallet(wallet);
		 }
		 modelAndView.addObject("successMessage","Money added successfully,available balance is: " +setAvalbal);
		return modelAndView;
	}
	@RequestMapping(value = "/transactionFailure/50", method = RequestMethod.GET)
	public ModelAndView TransactionFailure() {
		ModelAndView modelAndView = new ModelAndView("transactionFailure");
		modelAndView.addObject("failureMessage","Transaction failed due to some reason .Please try again");
		return modelAndView;
	}
	
	@RequestMapping(value = "/transactionFailure/50", method = RequestMethod.POST)
	public ModelAndView PostTransactionFailure() {
		ModelAndView modelAndView = new ModelAndView("transactionFailure");
		
		return modelAndView;
	}
	@RequestMapping(value = "/transactionCancel/50", method = RequestMethod.GET)
	public ModelAndView TransactionCancel() {
		ModelAndView modelAndView = new ModelAndView("transactionCancel");
		modelAndView.addObject("cancelMessage","Transaction Cancelled due to some reason .Please try again");
		return modelAndView;
	}
	
	@RequestMapping(value = "/transactionCancel/50", method = RequestMethod.POST)
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
	public ModelAndView createNewpassengerTrip(@Valid PassengerTrip passengerTrip, HttpServletRequest request,HttpServletResponse response, BindingResult bindingResult) {
		System.out.println("In createNewpassengerTrip POST Method");
		Passenger passenger=passengerService.getPhoneFromCookie(request, response);
		ModelAndView modelAndVieW=new ModelAndView();
		double walletAvlBal=0;
		modelAndVieW.setViewName("passengerTrip");
		double tikcetCost=passengerService.getTicketCost(passengerTrip.getNots(),passengerTrip.getFromloc(),passengerTrip.getToloc());
		try{
			walletAvlBal=passengerService.findWalletByPassenger(passenger).getAvalbal();
			
		}
		catch(NullPointerException e)
		{
			System.out.println("NullPointerException:"+e.getMessage());
			walletAvlBal=0;
		}
		finally{
			if(tikcetCost<0)
			{
				modelAndVieW.addObject("successMessage","Can't get distance, Please add route");
			}
			else if(tikcetCost==0)
			{
				modelAndVieW.addObject("successMessage","Can't get distance,From location and to location should not be same");
			}
			else
			{
				if(tikcetCost<=walletAvlBal )
				{
					passengerTrip.setPassenger(passenger);
					passengerTrip.setTktamt(tikcetCost);
					passengerService.savePassengerTrip(passengerTrip);
					modelAndVieW.addObject("successMessage","Ticket booked successfully");
				}
				else
				{
		
					modelAndVieW.addObject("successMessage","Not enough balance in wallet.Please add Money to wallet");
				}
			}
		}
		return modelAndVieW;
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

	/*@RequestMapping(value="/successfulPayed",method=RequestMethod.GET)
     public ModelAndView successfulPayed(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("In createsuccessfulPayed Method");
		double avlBal=0;
		double tktamt;
		ModelAndView modelAndView = new ModelAndView();

		try {
			System.out.println("Pid:" + passengerService.getPhoneFromCookie(request, response).getPid());
			tktamt = passengerService.getTicketAmt(passengerService.getPhoneFromCookie(request, response)).getTktamt();
			//avlBal = passengerService.getAvailBal(passengerService.getPhoneFromCookie(request, response)).getAvalbal();
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
	}*/

	@RequestMapping(value = "/successfulPayed", params = "Done", method = RequestMethod.POST)
	public String done() {
		return "redirect:/passenger";
	}

	
	
	
	
	@RequestMapping(value = "/wallet", method = RequestMethod.GET)
	public ModelAndView wallet(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("In wallet GET Method");
		ModelAndView modelAndView = new ModelAndView();
		Wallet wallet=new Wallet();
		double walletAvlBal=0;
		Passenger plocal=passengerService.getPhoneFromCookie(request,response);
		System.out.println("passengerService.isWalletExist(plocal)"+passengerService.isWalletExist(plocal));
		if(passengerService.isWalletExist(plocal)==true)
		{		System.out.println("Inside if");
				walletAvlBal=passengerService.findWalletByPassenger(plocal).getAvalbal();
		}
		
	    
	    System.out.println("avlBal in wallet GET method:"+avlBal);
	    //wallet.setAvalbal(walletAvlBal);
		modelAndView.setViewName("wallet");
		modelAndView.addObject("wallet",wallet);
		modelAndView.addObject("walletAvlBal",walletAvlBal);
		return modelAndView;
	}
	
	
	@RequestMapping(value="/wallet" ,method=RequestMethod.POST)
    public ModelAndView debit(@Valid Wallet wallet, BindingResult bindingResult,HttpServletRequest request, HttpServletResponse response,RedirectAttributes redir){
		ModelAndView modelAndView = new ModelAndView();
		System.out.println("In wallet POST method");
		wallet.setAvalbal(avlBal);
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
				Location fl1=new Location();
				fl1.setLoc(addloc.getFromloc());
				passengerService.saveLocation(fl1);
				
				Location tl2=new Location();
				tl2.setLoc(addloc.getToloc());
				passengerService.saveLocation(tl2);
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
		modelAndView.addObject("passengerTrip",passengerTrip);
		System.out.println(passengerTrip.toString());
		///TicketPdf tpdf=new TicketPdf();
		//tpdf.main1(null);
		
		
	
		return modelAndView;
	}
}
	
	
