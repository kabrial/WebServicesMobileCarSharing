package com.carSharing.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.carSharing.form.FamilyForm;
import com.carSharing.form.ReservedForm;
import com.carSharing.model.Child;
import com.carSharing.model.Excursion;
import com.carSharing.model.ExcursionGroup;
import com.carSharing.model.Group;
import com.carSharing.model.Trip;
import com.carSharing.model.TripChild;
import com.carSharing.model.TripChildPK;
import com.carSharing.model.TripParent;
import com.carSharing.model.TripParentPK;
import com.carSharing.model.User;
import com.carSharing.repository.ChildrenRepository;
import com.carSharing.repository.ExcursionRepository;
import com.carSharing.repository.TripChildRepository;
import com.carSharing.repository.TripParentRepository;
import com.carSharing.repository.TripRepository;
import com.carSharing.service.ChildrenService;
import com.carSharing.service.TripService;
import com.carSharing.service.UserService;

import lombok.AllArgsConstructor;

/**
 * Reserved Trip Controller
 * 
 * @author Kevin ABRIAL & Amine IDIR & Alexis BARTHELEMY
 */
@Controller
@AllArgsConstructor
public class DeleteTripController {

	// Repository
	@Autowired
	private ChildrenService childrenService;

	@Autowired
	private TripService tripService;

	@Autowired
	TripRepository tripRepository;

	@Autowired
	private UserService userService;

	@Autowired
	ExcursionRepository excursionRepository;

	@Autowired
	TripParentRepository tripParentRepository;

	@Autowired
	TripChildRepository tripChildRepository;

	@Autowired
	ChildrenRepository childrenRepository;

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String display(@PathVariable Long id, Model model) {

		model.addAttribute("id", id);

		Trip theTrip = tripService.findOne(id);
		List<Trip> trips = tripService.findAllById(theTrip.getExcursion().getId());
		trips.remove(theTrip);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findByUsername(auth.getName());
		List<Child> childsOfTheUser = childrenRepository.findByParent(user);
		for (Child child : childsOfTheUser) {
			List<TripChild> tripChildsOfTheUser = tripChildRepository.findByTripAndChild(theTrip, child);
			for (TripChild tripChild : tripChildsOfTheUser) {
				tripChildRepository.delete(tripChild);
			}
		}

		if (user.equals(theTrip.getParent())) {// si c'est la personne qui a cr�e le trip
			List<TripChild> tripChilds = tripChildRepository.findByTrip(theTrip);
			List<TripParent> tripParents = tripParentRepository.findByTrip(theTrip);

			List<TripChild> tripChildsTemp = tripChildRepository.findByTrip(theTrip);

			List<FamilyForm> families = new ArrayList<>();

			// regrouper en famille (parent enfant)
			for (TripParent tripParent : tripParents) {
				FamilyForm family = new FamilyForm();
				family.setParent(tripParent.getParent());
				family.setNbrPersonnes(1);
				List<Child> children = new ArrayList<>();
				int i = 0;
				while (tripChildsTemp.size() > 0 && tripChildsTemp.size() > i) {
					TripChild tripChild = tripChildsTemp.get(i);
					if (tripChild.getChild().getParent().equals(tripParent.getParent())) {
						children.add(tripChild.getChild());
						family.setNbrPersonnes(family.getNbrPersonnes() + 1);
						tripChildsTemp.remove(i);
					}
					i++;
				}
				family.setChildren(children);
				if (!family.getParent().equals(user)) {
					families.add(family);
				}
			}

			// regrouper en famille (null enfants) -- cas children seuls
			while (tripChildsTemp.size() > 0) {
				FamilyForm family = new FamilyForm();
				List<Child> children = new ArrayList<>();
				children.add(tripChildsTemp.get(0).getChild());
				family.setNbrPersonnes(1);
				tripChildsTemp.remove(0);
				int i = 0;
				while (tripChildsTemp.size() > 0 && tripChildsTemp.size() > i) {
					if (tripChildsTemp.get(i).getChild().getParent().equals(children.get(0).getParent())) {
						children.add(tripChildsTemp.get(i).getChild());
						family.setNbrPersonnes(family.getNbrPersonnes() + 1);
						tripChildsTemp.remove(i);
					} else {
						i++;
					}
				}
				family.setChildren(children);

				if (!family.getChildren().get(0).getParent().equals(user)) {
					families.add(family);
				}
			}

			// chercher les trip qui ont de la place pour une famille (famille par famille)
			// cr�er le lien et save
			int indexT = 0;
			while (trips.size() > 0 && trips.size() > indexT) {
				int indexF = 0;
				while (families.size() > 0 && families.size() > indexF) {
					if ((trips.get(indexT).getNumberPlaces() - trips.get(indexT).getPlacesReserved()) >= families
							.get(indexF).getNbrPersonnes()) {// s'il ya de la place pour toute la famille
						if (families.get(indexF).getChildren().size() < families.get(indexF).getNbrPersonnes()) {// si
																													// le
																													// parent
																													// est
																													// pr�sent
																													// cr�er
																													// le
																													// lien
							TripParent tripParent = new TripParent();
							TripParentPK tripParentPK = new TripParentPK();
							tripParentPK.setIdParent(families.get(indexF).getParent().getId());
							tripParentPK.setIdTrip(trips.get(indexT).getId());
							tripParent.setTrip(trips.get(indexT));
							tripParent.setParent(families.get(indexF).getParent());
							tripParent.setId(tripParentPK);
							tripParentRepository.save(tripParent);
						}
						// cr�er les liens avec tout les enfants
						for (Child child : families.get(indexF).getChildren()) {
							TripChild tripChild = new TripChild();
							TripChildPK tripChildPK = new TripChildPK();
							tripChildPK.setIdChild(child.getId());
							tripChildPK.setIdTrip(trips.get(indexT).getId());
							tripChild.setTrip(trips.get(indexT));
							tripChild.setChild(child);
							tripChild.setId(tripChildPK);
							tripChildRepository.save(tripChild);
						}
						trips.get(indexT).setPlacesReserved(
								trips.get(indexT).getPlacesReserved() + families.get(indexF).getNbrPersonnes());// reserver
																												// pour
																												// toute
																												// la
																												// famille
						tripRepository.save(trips.get(indexT));
					}
					indexF++;
				}

				if ((trips.get(indexT).getNumberPlaces() - trips.get(indexT).getPlacesReserved()) == 0) {// si il y'a
																											// plus de
																											// places
																											// dans le
																											// trip le
																											// supprimer
																											// de la
																											// liste des
																											// trips
																											// possibles
					trips.remove(indexT);
				} else {
					indexT++;
				}
			}

			// placer les familles dans les trips ou il reste des places personne par
			// personne
			while (families.size() > 0 && trips.size() > 0) {
				if ((trips.get(0).getNumberPlaces() - trips.get(0).getPlacesReserved()) == 0) {
					trips.remove(0);
				} else {
					long placesAvailable = trips.get(0).getNumberPlaces() - trips.get(0).getPlacesReserved();
					if (families.get(0).getChildren().size() < families.get(0).getNbrPersonnes()) {
						TripParent tripParent = new TripParent();
						TripParentPK tripParentPK = new TripParentPK();
						tripParentPK.setIdParent(families.get(0).getParent().getId());
						tripParentPK.setIdTrip(trips.get(0).getId());
						tripParent.setTrip(trips.get(0));
						tripParent.setParent(families.get(0).getParent());
						tripParent.setId(tripParentPK);
						tripParentRepository.save(tripParent);
						placesAvailable--;
						trips.get(0).setPlacesReserved(trips.get(0).getPlacesReserved() + 1);
						tripRepository.save(trips.get(0));
						families.get(0).setNbrPersonnes(families.get(0).getNbrPersonnes() - 1);
					}
					while (families.get(0).getChildren().size() > placesAvailable) {
						TripChild tripChild = new TripChild();
						TripChildPK tripChildPK = new TripChildPK();
						tripChildPK.setIdChild(families.get(0).getChildren().get(0).getId());
						tripChildPK.setIdTrip(trips.get(0).getId());
						tripChild.setTrip(trips.get(0));
						tripChild.setChild(families.get(0).getChildren().get(0));
						tripChild.setId(tripChildPK);
						tripChildRepository.save(tripChild);
						placesAvailable--;
						trips.get(0).setPlacesReserved(trips.get(0).getPlacesReserved() + 1);
						tripRepository.save(trips.get(0));
						families.get(0).setNbrPersonnes(families.get(0).getNbrPersonnes() - 1);
						families.get(0).getChildren().remove(0);
					}
				}
				if (families.get(0).getNbrPersonnes() == 0) {
					families.remove(0);
				}
			}

			// supprimer le trip et les tables de liens
			for (TripParent tripParent : tripParents) {
				tripParentRepository.delete(tripParent);
			}
			for (TripChild tripChild : tripChilds) {
				tripChildRepository.delete(tripChild);
			}
			tripRepository.delete(theTrip);
		} else {
			return ("redirect:/trips/" + theTrip.getExcursion().getId());
		}
		return ("redirect:/trips/" + theTrip.getExcursion().getId());
	}
}
