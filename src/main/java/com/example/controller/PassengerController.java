package com.example.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.model.DebitCard;
import com.example.model.Netbanking;
import com.example.model.OtherPay;
import com.example.model.Passenger;
import com.example.model.PassengerOtp;
import com.example.model.PassengerTrip;
import com.example.model.Wallet;
import com.example.model.WalletDebit;
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

	@RequestMapping(value = "/passenger", method = RequestMethod.POST)
	public String createNewpassenger(@Valid Passenger passenger, HttpServletRequest request,HttpServletResponse response, BindingResult bindingResult) {
		System.out.println("In passenger Post meth:" + passenger.getPhoneNo());
		passengerService.savePassenger(passenger);
		passengerService.setPhoneToCookie(request, response, passenger.getPhoneNo());
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
		ModelAndView modelAndView = new ModelAndView();
		PassengerTrip passengerTrip = new PassengerTrip();
		modelAndView.addObject("passengerTrip", passengerTrip);
		modelAndView.setViewName("passengerTrip");
		return modelAndView;
	}

	
	@RequestMapping(value = "/passengerTrip", method = RequestMethod.POST)
	public String createNewpassengerTrip(@Valid PassengerTrip passengerTrip, HttpServletRequest request,HttpServletResponse response, BindingResult bindingResult) {
		System.out.println("In createNewpassengerTrip POST Method");
		passengerTrip.setPassenger(passengerService.getPhoneFromCookie(request, response));
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
	
	
	@RequestMapping(value="/wallet", params="Debit" ,method=RequestMethod.POST)
    public String debit(@Valid Wallet wallet, BindingResult bindingResult,HttpServletRequest request, HttpServletResponse response,RedirectAttributes redir){
		System.out.println("In wallet POST method");
		//wallet.setAvalbal(avlBal);
		wallet.setPassenger(passengerService.getPhoneFromCookie(request, response));
		System.out.println("from passed wallet object avlBal:" +wallet.getAvalbal());
		redir.addFlashAttribute("redrWallet", wallet);
		
        return "redirect:/walletDebit";
		//return "wallet";
      }
	
	
	/*@ModelAttribute("modelWallet")
	public Wallet getWallet(Wallet w) {
	   
	      return countries;
	   }*/

	@RequestMapping(value="/wallet",params="Net",method=RequestMethod.GET)
    public String net(){
        return "redirect:/netbanking";
    }
	
	@RequestMapping(value="/wallet",params="other",method=RequestMethod.GET)
    public String other(){
        return "redirect:/otherPay";
    }

	@RequestMapping(value = "/walletDebit", method = RequestMethod.GET)
	public ModelAndView debitCard(@ModelAttribute("redrWallet") Wallet wallet,RedirectAttributes redir,HttpServletRequest request, HttpServletResponse response) {
		System.out.println("In Debit card GET method");
		System.out.println("Redirect Attributes:"+wallet.getAddamt()+wallet.getAvalbal());
		ModelAndView modelAndView = new ModelAndView();
		DebitCard debitCard = new DebitCard();
		WalletDebit wd=new WalletDebit();
		wd.setDebitCard(debitCard);
		wd.setWallet(wallet);
		modelAndView.addObject(wd);
		return modelAndView;
	}
	
	/*@RequestMapping(value = "/debitCard", method = RequestMethod.GET)
	public String debitCard(@ModelAttribute("redrWallet") Wallet wallet,Model map) {
		
		System.out.println("In Debit card GET method");
		System.out.println("Redirect Attributes:"+wallet.getAddamt()+wallet.getAvalbal());
		DebitCard debitCard = new DebitCard();
		map.addAttribute("arrDebitCard",debitCard);
		map.addAttribute("arrWalet",wallet);
		//redir.addFlashAttribute("redrWallet", wallet);
		return "debitCard";
	}*/

	/*@RequestMapping(value = "/debitCard", method = RequestMethod.POST)
	public String createNewdebitCard(@Valid DebitCard debitCard,HttpServletRequest request,
			HttpServletResponse response,BindingResult bindingResult,@ModelAttribute("redrWallet") Wallet wallet, BindingResult walletBResult) {
		System.out.println("In createNewdebitCard POST method");
		debitCard.setPassenger(passengerService.getPhoneFromCookie(request, response));
		passengerService.saveDebitCard(debitCard);
		System.out.println("After adding debitCard");
		System.out.println("wallet.getAddamt()"+wallet.getAddamt());
		System.out.println(wallet.getAvalbal());
		
		//System.out.println("Redirect values are ;"+"wallet.getAddamt():"+wallet.getAddamt()+ "wallet.getAvalbal():"+wallet.getAvalbal()+ "wallet.getPassenger().getPid():"+ wallet.getPassenger().getPid());
		passengerService.saveWallet(wallet);
		
		return "debitCard";
	}*/
	
	@RequestMapping(value = "/walletDebit", method = RequestMethod.POST)
	public String createNewdebitCard(@Valid WalletDebit walletDebit,HttpServletRequest request,
			HttpServletResponse response,BindingResult bindingResult) {
		System.out.println("In createNewdebitCard POST method");
		walletDebit.getDebitCard().setPassenger(passengerService.getPhoneFromCookie(request, response));
		passengerService.saveDebitCard(walletDebit.getDebitCard());
		System.out.println("After adding debitCard");
		System.out.println("walletDebit.getWallet().getAddamt()"+walletDebit.getWallet().getAddamt());
		System.out.println(walletDebit.getWallet().getAvalbal());
		
		//System.out.println("Redirect values are ;"+"wallet.getAddamt():"+wallet.getAddamt()+ "wallet.getAvalbal():"+wallet.getAvalbal()+ "wallet.getPassenger().getPid():"+ wallet.getPassenger().getPid());
		passengerService.saveWallet(walletDebit.getWallet());
		
		return "walletDebit";
	}
	
	/*@RequestMapping(value = "/debitCard", method = RequestMethod.POST)
	public String createNewdebitCard(@Valid DebitCard debitCard,HttpServletRequest request,
			HttpServletResponse response,BindingResult bindingResult,@ModelAttribute("arrWalet") Wallet arrWalet, BindingResult walletBResult) {
		System.out.println("In createNewdebitCard POST method");
		debitCard.setPassenger(passengerService.getPhoneFromCookie(request, response));
		passengerService.saveDebitCard(debitCard);
		System.out.println("After adding debitCard");
		System.out.println("arrWalet.getAddamt()"+arrWalet.getAddamt());
		System.out.println(arrWalet.getAvalbal());
		
		//System.out.println("Redirect values are ;"+"wallet.getAddamt():"+wallet.getAddamt()+ "wallet.getAvalbal():"+wallet.getAvalbal()+ "wallet.getPassenger().getPid():"+ wallet.getPassenger().getPid());
		passengerService.saveWallet(arrWalet);
		
		return "debitCard";
	}*/
	
	
   @RequestMapping(value = "/netbanking", method = RequestMethod.GET)
	public ModelAndView netbanking() {
		ModelAndView modelAndView = new ModelAndView();
		Netbanking netbanking = new Netbanking();
		modelAndView.addObject("netbanking", netbanking);
		modelAndView.setViewName("netbanking");
		return modelAndView;
	}

	@RequestMapping(value = "/netbanking", method = RequestMethod.POST)
	public ModelAndView createNewdebitCard(@Valid Netbanking netbanking, BindingResult bindingResult) {
		System.out.println("In createNewnetbanking Method");
		ModelAndView modelAndView = new ModelAndView();
		passengerService.saveNetbanking(netbanking);
		modelAndView.addObject("netbanking", new Netbanking());
		modelAndView.setViewName("netbanking");
		// }
		return modelAndView;
	}

	@RequestMapping(value = "/otherPay", method = RequestMethod.GET)
	public ModelAndView otherPay() {
		ModelAndView modelAndView = new ModelAndView();
		OtherPay otherPay = new OtherPay();
		modelAndView.addObject("otherPay", otherPay);
		modelAndView.setViewName("otherPay");
		return modelAndView;
	}

	@RequestMapping(value = "/otherPay", method = RequestMethod.POST)
	public ModelAndView createNewotherPay(@Valid OtherPay otherPay, BindingResult bindingResult) {
		System.out.println("In createOtherPay Method");
		ModelAndView modelAndView = new ModelAndView();
		passengerService.saveOtherPay(otherPay);
		modelAndView.addObject("otherPay", new OtherPay());
		modelAndView.setViewName("otherPay");

		// }
		return modelAndView;
	}
	
	

}