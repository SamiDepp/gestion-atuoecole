package com.autoecole.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.autoecole.entities.Condidat;
import com.autoecole.entities.Moniteur;
import com.autoecole.entities.Seance;
import com.autoecole.entities.User;
import com.autoecole.service.AutoEcolesevices;
import com.autoecole.service.CondidatServices;
import com.autoecole.service.ExamenServices;
import com.autoecole.service.MoniteurServices;
import com.autoecole.service.PaiementServices;
import com.autoecole.service.RoleServices;
import com.autoecole.service.SeanceServices;
import com.autoecole.service.UserServices;
import com.autoecole.service.VehiculeServices;
import com.autoecole.model.ExamModel;
import com.autoecole.model.Loginmodel;
import com.autoecole.model.PayModel;
import com.autoecole.model.SeanceModel;
import com.autoecole.model.UserModel;
import com.autoecole.model.VehiculeModel;

/**
 * Handles requests for the application home page.
 */
@Controller
@Slf4j
public class MainController {

	@Autowired
	private UserServices userservice;

	@Autowired
	private RoleServices roleservice;

	@Autowired
	private AutoEcolesevices autoservice;
	
	
	@Autowired
	private ExamenServices examservice;

	@Autowired
	private SeanceServices seanceservice;

	@Autowired
	private VehiculeServices vehiculeservice;
	
	@Autowired
	private CondidatServices condidatservice;
	
	@Autowired
	private MoniteurServices moniteurservice;

	@Autowired
	private PaiementServices payservice;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {

		log.info("test query" + userservice.findByEmailAddress("male").size());

		log.info("test recherche by role" + userservice.findByRole(1l).size());

		return "index";

	}

	@RequestMapping(value = "addCondidat", method = RequestMethod.GET)
	public String addCondidat(Model model) {

		model.addAttribute("userList", userservice.findAll());
		model.addAttribute("userModel", new UserModel());
		model.addAttribute("ListRoles", roleservice.list());
		model.addAttribute("ListAuto", autoservice.list());

		return "addCondidat";

	}
	
	@RequestMapping(value = "addmoniteur", method = RequestMethod.GET)
	public String addmoniteur(Model model) {

		model.addAttribute("userList", moniteurservice.list());
		model.addAttribute("userModel", new UserModel());
		model.addAttribute("ListRoles", roleservice.list());
		model.addAttribute("ListAuto", autoservice.list());

		return "addmoniteur";

	}
	
	
	@RequestMapping(value = "saveMoniteur", method = RequestMethod.GET)
	public String saveMoniteur(Model model, @ModelAttribute("userModel") UserModel userModel) {
		log.info("salaire = " + userModel.getNom());
		log.info("userModel.getDate = " + userModel.getSalaire());

		userservice.create(userModel.getNom(), userModel.getPrenom(), convertdate(userModel.getDateNaiss()),
				userModel.getSexe(), userModel.getAdresse(), userModel.getNrotel(), userModel.getCin(),
				userModel.getEmail(), 2l, Long.parseLong(userModel.getAutoecole()), userModel.getSalaire());

		model.addAttribute("userModel", new UserModel());
		model.addAttribute("ListRoles", roleservice.list());
		model.addAttribute("userList", moniteurservice.list());
		model.addAttribute("ListAuto", autoservice.list());
		return "listmoniteur";
	}
	
	@RequestMapping(value = "editmoniteur", method = RequestMethod.GET)
	public String editmoniteur(Model model) {

		model.addAttribute("userModel", new UserModel());
		model.addAttribute("ListRoles", roleservice.list());
		model.addAttribute("userList", moniteurservice.list());
		model.addAttribute("ListAuto", autoservice.list());
		return "listmoniteur";
	}
	
	@RequestMapping(value = "deletMoniteur", method = RequestMethod.GET)
	public String deletMoniteur(Model model, @RequestParam("id") String id) {

		moniteurservice.delete(Long.parseLong(id));

		model.addAttribute("userModel", new UserModel());
		model.addAttribute("ListRoles", roleservice.list());
		model.addAttribute("userList", moniteurservice.list());
		model.addAttribute("ListAuto", autoservice.list());
		return "listmoniteur";

	}
	
	
	
	
	@RequestMapping(value = "updateMoniteur", method = RequestMethod.GET)
	public String updateMoniteur(Model model, @RequestParam("id") String id) {

		model.addAttribute("moniteur", moniteurservice.getbyid(Long.parseLong(id)));
		

		return "UpdateMoniteur";

	}
	
	
	
	@RequestMapping(value = "Moniteurupdate", method = RequestMethod.GET)
	public String updateUser(Model model, @ModelAttribute("userModel") UserModel userModel) {

		model.addAttribute("userModel", new UserModel());
		model.addAttribute("ListRoles", roleservice.list());
		model.addAttribute("userList", moniteurservice.list());
		model.addAttribute("ListAuto", autoservice.list());

		return "listmoniteur";

	}
	
	
	

	@RequestMapping(value = "listCondidat", method = RequestMethod.GET)
	public String listCondidat(Model model) {

		model.addAttribute("userList", userservice.findAll());
		model.addAttribute("userModel", new UserModel());
		model.addAttribute("ListRoles", roleservice.list());
		model.addAttribute("ListAuto", autoservice.list());

		return "listcandidat";

	}

	@RequestMapping(value = "saveCondidat", method = RequestMethod.GET)
	public String saveCondidat(Model model, @ModelAttribute("userModel") UserModel userModel) {
		log.info("userModel.getNom() = " + userModel.getNom());
		log.info("userModel.getDate = " + userModel.getDateNaiss());

		userservice.create(userModel.getNom(), userModel.getPrenom(), convertdate(userModel.getDateNaiss()),
				userModel.getSexe(), userModel.getAdresse(), userModel.getNrotel(), userModel.getCin(),
				userModel.getEmail(), 3l, Long.parseLong(userModel.getAutoecole()), userModel.getSalaire());

		model.addAttribute("userModel", new UserModel());
		model.addAttribute("ListRoles", roleservice.list());
		model.addAttribute("userList", userservice.findAll());
		model.addAttribute("ListAuto", autoservice.list());
		return "listcandidat";
	}
	
	@RequestMapping(value = "savevehicule", method = RequestMethod.GET)
	public String savevehicule(Model model, @ModelAttribute("VehiculeModel") VehiculeModel vModel) {
		
		log.info(""+vModel.getAssurance());
		log.info(""+vModel.getCouleur());
		log.info(""+vModel.getMarque());
		log.info(""+vModel.getMatricule());
		log.info(""+vModel.getModele());
		vehiculeservice.save(vModel.getMatricule(), vModel.getModele(), vModel.getMarque(), vModel.getCouleur(),
				vModel.getAssurance());
		model.addAttribute("listevehicule", vehiculeservice.listall());
	
		return "listvehicule";
	}
	
	
	@RequestMapping(value = "addvehicule", method = RequestMethod.GET)
	public String addvehicule(Model model) {
	
		return "addvehicule";
	}
	
	
	@RequestMapping(value = "listvehicule", method = RequestMethod.GET)
	public String listvehicule(Model model) {
		model.addAttribute("listevehicule", vehiculeservice.listall());
		return "listvehicule";
	}
	

	@RequestMapping(value = "deletUser", method = RequestMethod.GET)
	public String deletUser(Model model, @RequestParam("id") String id) {

		userservice.delete(Long.parseLong(id));

		model.addAttribute("userList", userservice.findAll());
		model.addAttribute("userModel", new UserModel());
		model.addAttribute("ListRoles", roleservice.list());
		model.addAttribute("ListAuto", autoservice.list());

		return "addCondidat";

	}
	
	@RequestMapping(value = "deleteVehicule", method = RequestMethod.GET)
	public String deleteVehicule(Model model, @RequestParam("id") String id) {

		vehiculeservice.delete(Long.parseLong(id));

		model.addAttribute("listevehicule", vehiculeservice.listall());
		return "listvehicule";

	}
	
	
	@RequestMapping(value = "updatVehicule", method = RequestMethod.GET)
	public String updatVehicule(Model model,@ModelAttribute("VehiculeModel") VehiculeModel vModel) {

		vehiculeservice.update(Long.parseLong(vModel.getId()), vModel.getMatricule(), vModel.getModele(), vModel.getMarque(), vModel.getCouleur(), vModel.getAssurance());

		model.addAttribute("listevehicule", vehiculeservice.listall());
		return "listvehicule";

	}
	
	@RequestMapping(value = "updateVehicule", method = RequestMethod.GET)
	public String updateVehicule(Model model, @RequestParam("id") String id) {

		vehiculeservice.getbyid(Long.parseLong(id));

		model.addAttribute("vehicule", vehiculeservice.getbyid(Long.parseLong(id)));
		return "addvehicule";

	}
	
	

	
	

	@RequestMapping(value = "updateUser", method = RequestMethod.GET)
	public String updateUser(Model model, @RequestParam("id") String id) {

		model.addAttribute("user", userservice.getOne(Long.parseLong(id)));
		model.addAttribute("userList", userservice.findAll());
		model.addAttribute("userModel", new UserModel());
		model.addAttribute("ListRoles", roleservice.list());
		model.addAttribute("ListAuto", autoservice.list());

		return "UpdateCondidat";

	}

	@RequestMapping(value = "addseance", method = RequestMethod.GET)
	public String addseance(Model model) {

		model.addAttribute("ListCandidat", condidatservice.listAll());
		model.addAttribute("ListMoniteur", moniteurservice.list());
		model.addAttribute("ListVehicule", vehiculeservice.listall());

		return "addseance";

	}

	@RequestMapping(value = "saveSeance", method = RequestMethod.GET)
	public String saveSeance(Model model, @ModelAttribute("seanceModel") SeanceModel smodel) {

		log.info("candidat" + smodel.getCandidat());
		log.info("getMoniteur" + smodel.getMoniteur());
		log.info("getVehicule" + smodel.getVehicule());
		log.info("getDateseance" + smodel.getDateseance());
		log.info("getHeuredeb" + smodel.getHeuredeb());
		log.info("getHeurefin" + smodel.getHeurefin());
		log.info("convertdatefomat2" + convertdatefomat2(smodel.getHeurefin()));

		Seance seance = seanceservice.create(convertdate(smodel.getDateseance()),
				convertdatefomat2(smodel.getHeuredeb()), convertdatefomat2(smodel.getHeurefin()), "1",
				Long.parseLong(smodel.getCandidat()), Long.parseLong(smodel.getVehicule()),
				Long.parseLong(smodel.getMoniteur()));

		log.info("seance créer" + seance.getId());
		model.addAttribute("seanceList", seanceservice.lisAll());

		return "listseance";

	}

	@RequestMapping(value = "deletSeance", method = RequestMethod.GET)
	public String deletSeance(Model model, @RequestParam("id") String id) {

		seanceservice.delete(Long.parseLong(id));
		model.addAttribute("seanceList", seanceservice.lisAll());

		return "listseance";

	}

	@RequestMapping(value = "updateSeanceview", method = RequestMethod.GET)
	public String updateSeanceView(Model model, @RequestParam("id") String id) {
		
		Seance seance = seanceservice.getbyid(Long.parseLong(id));
		model.addAttribute("dateseance", seance.getDate_seance());
		model.addAttribute("heuredeb", seance.getHeure_deb());
		model.addAttribute("heurefin", seance.getHeure_fin());
		model.addAttribute("idseance", id);
	

//		model.addAttribute("seanceList", seanceservice.lisAll());

		return "updateseance";

	}
	
	@RequestMapping(value = "updateSeance", method = RequestMethod.GET)
	public String updateSeance(Model model, @ModelAttribute("seanceModel") SeanceModel smodel) {
		
log.info("idseance"+smodel.getIdseance());
	
	   seanceservice.update(Long.parseLong(smodel.getIdseance()),convertdate(smodel.getDateseance()),
				convertdatefomat2(smodel.getHeuredeb()), convertdatefomat2(smodel.getHeurefin()),
				smodel.getCandidat(), smodel.getVehicule(),
				smodel.getMoniteur());
	   
//		model.addAttribute("seanceList", seanceservice.lisAll());

	   model.addAttribute("seanceList", seanceservice.lisAll());

		return "listseance";

	}

	@RequestMapping(value = "listseance", method = RequestMethod.GET)
	public String listseance(Model model) {

		model.addAttribute("seanceList", seanceservice.lisAll());

		return "listseance";

	}
	
	@RequestMapping(value = "addexam", method = RequestMethod.GET)
	public String addexam(Model model) {

		model.addAttribute("ListCandidat", condidatservice.listAll());
		model.addAttribute("ListMoniteur", moniteurservice.list());

		return "addexamen";

	}
	
	@RequestMapping(value = "paiementview", method = RequestMethod.GET)
	public String paiementview(Model model) {

		model.addAttribute("ListCandidat", condidatservice.listAll());
		model.addAttribute("ListMoniteur", moniteurservice.list());

		return "addpaiement";

	}
	
	@RequestMapping(value = "savePay", method = RequestMethod.GET)
	public String savePay(Model model,@ModelAttribute("PayModel") PayModel pmodel) {

		
		payservice.create(Long.parseLong(pmodel.getCandidat()), Long.parseLong(pmodel.getMoniteur()), Integer.parseInt(pmodel.getNbheure()), Double.parseDouble(pmodel.getTotal()), Integer.parseInt(pmodel.getNbabsecence()));
		

		return "addpaiement";

	}
	
	
	@RequestMapping(value = "listpaiementview", method = RequestMethod.GET)
	public String listpaiementview(Model model) {

		
		model.addAttribute("listPay", payservice.listAll());

		return "listepaiement";

	}
	
	
	
	
	@RequestMapping(value = "saveExam", method = RequestMethod.GET)
	public String saveExam(Model mode,@ModelAttribute("ExamModel") ExamModel emodel) {
log.info("moniteur"+emodel.getMoniteur());
	
		examservice.create(convertdatefomat2(emodel.getHeuredeb()), convertdatefomat2(emodel.getHeurefin()), emodel.getType(), emodel.getResultat(), emodel.getCandidat(), emodel.getMoniteur());
		return "addexamen";

	}
	
	
	@RequestMapping(value = "Listeexam", method = RequestMethod.GET)
	public String Listexam(Model model) {

	
		model.addAttribute("ListExam", examservice.listAll());
		return "listexamen";

	}
	
	
	
	
	

	@RequestMapping(value = "updateCondidat", method = RequestMethod.GET)
	public String updateCondidat(Model model, @ModelAttribute("userModel") UserModel userModel) {

		userservice.update(userModel.getId(), userModel.getNom(), userModel.getPrenom(), new Date(),
				userModel.getSexe(), userModel.getAdresse(), userModel.getNrotel(), userModel.getCin(),
				userModel.getEmail(), null, null, null);
		model.addAttribute("userList", userservice.findAll());
		model.addAttribute("userModel", new UserModel());
		model.addAttribute("ListRoles", roleservice.list());
		model.addAttribute("ListAuto", autoservice.list());

		return "addCondidat";

	}

	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String index(Model model) {

		return "index";

	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(Model model, @ModelAttribute("Loginmodel") Loginmodel logmod, HttpServletResponse resp) {

		String page = null;

		log.info("username" + logmod.getUsername());
		log.info("password" + logmod.getPassword());
		User user = userservice.findByUsernameLike(logmod.getUsername());

		String Password = user.getPassword();
		log.info("password from database" + user.getPassword());
		if (user != null) {

			if (logmod.getPassword().equals(Password)) {

				if (user.getRole().getId() == 1) {

					model.addAttribute("userList", userservice.findAll());
					model.addAttribute("userModel", new UserModel());
					model.addAttribute("ListRoles", roleservice.list());
					model.addAttribute("ListAuto", autoservice.list());
					log.info("admiiiiiiiiiiiiin" );

					page = "dashboardAdmin";
				}
				
				 else if(user.getRole().getId() == 2){
						//moniteur
						Moniteur moniteur = moniteurservice.getbyuser(user.getId());
						model.addAttribute("seanceList", seanceservice.listbyMoniteur(moniteur.getId()));

						page = "listseanceMoniteur";
						log.info("moniteeeeeur"+seanceservice.listbyMoniteur(moniteur.getId()).size() );
						
					}
					else if(user.getRole().getId() == 3){
						Condidat condidat = condidatservice.getbyuser(user.getId());
						model.addAttribute("seanceList", seanceservice.listbyCondidat(condidat.getId()));

						page = "listseanceCondidat";
						log.info("condidat" );
						
					}else{

						page = "login";
						model.addAttribute("msg", "Login error !!");
						model.addAttribute("exist", "false");
					}

			}else{

				page = "login";
				model.addAttribute("msg", "Login error !!");
				model.addAttribute("exist", "false");
			}
		}

		return page;

	}

	@RequestMapping(value = "authentification", method = RequestMethod.GET)
	public String authentification(Model model) {

		log.info("size roles" + roleservice.list().size());
		model.addAttribute("ListRoles", roleservice.list());
		model.addAttribute("ListAuto", autoservice.list());

		return "login";

	}

	// public Date convertdate(String dateinput){
	//
	// SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	// String dateInString = dateinput;
	//
	// try {
	//
	// Date date = formatter.parse(dateInString);
	// log.info(""+date);
	// log.info(formatter.format(date));
	// return date ;
	//
	// } catch (ParseException e) {
	// e.printStackTrace();
	// return null ;
	// }
	//
	//
	// }

	public Date convertdate(String dateStr) {

		DateFormat readFormat = new SimpleDateFormat("MM/dd/yyyy");
		DateFormat writeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date = null;
		String result = null;
		try {
			date = readFormat.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (date != null) {
			String formattedDate = writeFormat.format(date);
			result = formattedDate;
		}
		return date;
	}

	public Date convertdatefomat2(String dateStr) {

		DateFormat readFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		DateFormat writeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date = null;
		String result = null;
		try {
			date = readFormat.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (date != null) {
			String formattedDate = writeFormat.format(date);
			result = formattedDate;
		}
		return date;
	}

}
