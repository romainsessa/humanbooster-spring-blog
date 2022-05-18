package com.hb.springpersistence;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.hb.springpersistence.dto.PostCommentDTO;
import com.hb.springpersistence.dto.PostDTO;
import com.hb.springpersistence.dto.PostDetailsDTO;
import com.hb.springpersistence.dto.TagDTO;
import com.hb.springpersistence.service.ICommentService;
import com.hb.springpersistence.service.IPostService;
import com.hb.springpersistence.service.ITagService;

//@Component
public class EntryPoint implements CommandLineRunner {

	private static Logger logger = LoggerFactory.getLogger(EntryPoint.class);

	@Autowired
	private IPostService pService;
	@Autowired
	private ICommentService cService;
	@Autowired
	private ITagService tService;

	@Override
	public void run(String... args) throws Exception {

		boolean continueApp = true;
		Scanner sc = new Scanner(System.in);

		while (continueApp) {

			boolean askEntity = true;
			int entityChoice = 0;
			do {
				logger.info("Quelle entité souhaitez-vous utiliser ? \n 1. Post \n 2. Comment \n 3. Tag");
				entityChoice = Integer.parseInt(sc.nextLine());
				if (entityChoice == 1 || entityChoice == 2 || entityChoice == 3) {
					askEntity = false;
				} else {
					logger.warn("Attention, vous devez choisir une entité entre 1, 2 et 3.");
				}
			} while (askEntity);

			boolean askAction = true;
			int actionChoice = 0;
			do {
				String txt = "Quelle action souhaitez-vous effectuer ? \n 1. Creer \n 2. Lire \n 3. Mettre à jour \n 4. Supprimer";
				if (entityChoice == 1 || entityChoice == 3) {
					txt += "\n 5. Associer un post à un tag";
				}
				logger.info(txt);
				actionChoice = Integer.parseInt(sc.nextLine());

				if (actionChoice == 1 || actionChoice == 2 || actionChoice == 3 || actionChoice == 4
						|| actionChoice == 5) {
					askAction = false;
				} else {
					logger.warn("Attention, vous devez choisir un numéro d'action entre 1, 2, 3, 4 et 5");
				}
			} while (askAction);

			switch (actionChoice) {
			case 1:
				if (entityChoice == 1) {
					logger.info("Quel est le titre du post ?");
					String title = sc.nextLine();
					PostDTO p = new PostDTO();
					p.setTitle(title);
					logger.info("Qui a créé le post ?");
					String createdBy = sc.nextLine();
					PostDetailsDTO detail = new PostDetailsDTO();
					detail.setCreatedBy(createdBy);
					detail.setCreatedOn(new Date());
					p.setDetails(detail);
					pService.save(p);
				} else if (entityChoice == 2) {
					logger.info("Quel est le contenu du commentaire ?");
					String review = sc.nextLine();
					logger.info("Quel est l'id du post associé ? ");
					int postId = Integer.parseInt(sc.nextLine());
					PostCommentDTO c = new PostCommentDTO();
					c.setReview(review);
					PostDTO p = pService.getPostDTO(postId);
					p.getComments().add(c);
					pService.save(p);
				} else if (entityChoice == 3) {
					logger.info("Quel est le nom du tag ?");
					String name = sc.nextLine();
					TagDTO t = new TagDTO();
					t.setName(name);
					tService.save(t);
				}
				break;
			case 2:
				if (entityChoice == 1) {
					List<PostDTO> posts = pService.getPostDTOs();
					for (PostDTO p : posts) {
						logger.info(p.toString());
					}
				} else if (entityChoice == 2) {
					List<PostCommentDTO> comments = cService.getCommentDTOs();
					for (PostCommentDTO c : comments) {
						logger.info(c.toString());
					}
				} else if (entityChoice == 3) {
					List<TagDTO> tags = tService.getTagDTOs();
					for (TagDTO t : tags) {
						logger.info(t.toString());
					}
				}
				break;
			case 3:
				if (entityChoice == 1) {
					logger.info("Quel est l'id du post à modifier ?");
					int postId = Integer.parseInt(sc.nextLine());
					PostDTO p = pService.getPostDTO(postId);
					logger.info(
							"Quel est le nouveau titre du post ? (Appuyez juste sur entrée pour garder le même titre)");
					String title = sc.nextLine();
					if (!title.equals("")) {
						p.setTitle(title);
					}
					logger.info("Quel est le nouvel auteur ? (Appuyez juste sur entrée pour garder le même titre)");
					String createdBy = sc.nextLine();
					if (!createdBy.equals("")) {
						p.getDetails().setCreatedBy(createdBy);
					}
					logger.info("La date de création ne peut pas être mise à jour");
					pService.save(p);
				} else if (entityChoice == 2) {
					logger.info("Quel est l'id du commentaire à modifier ?");
					int commentId = Integer.parseInt(sc.nextLine());
					logger.info("Quel est le nouveau contenu du commentaire ?");
					String review = sc.nextLine();
					PostCommentDTO c = cService.getCommentDTO(commentId);
					c.setReview(review);
					cService.save(c);
				} else if (entityChoice == 3) {
					logger.info("Quel est l'id du tag à modifier ?");
					int tagId = Integer.parseInt(sc.nextLine());
					logger.info("Quel est le nouveau nom du tag ?");
					String name = sc.nextLine();
					TagDTO t = tService.getTagDTO(tagId);
					t.setName(name);
					tService.save(t);
				}
				break;
			case 4:
				logger.info("Quel est l'id de l'entité à supprimer ? ");
				int id = Integer.parseInt(sc.nextLine());
				if (entityChoice == 1) {
					pService.delete(id);
				} else if (entityChoice == 2) {
					cService.delete(id);
				} else if (entityChoice == 3) {
					tService.delete(id);
				}
				break;
			case 5:
				if (entityChoice == 1 || entityChoice == 3) {
					logger.info("Quel est l'id du post ?");
					int postId = Integer.parseInt(sc.nextLine());
					logger.info("Quel est l'id du tag ?");
					int tagId = Integer.parseInt(sc.nextLine());
					pService.mapPostTag(postId, tagId);
				} else if (entityChoice == 2) {
					logger.warn("Attention, le numéro d'action 5 n'est pas valide pour l'entité Comment.");
				}
				break;
			}

			logger.info(
					"Voulez-vous continuer à utiliser l'app ? Si oui tapez 1, sinon tappez n'importe quel chiffre.");
			int rep = Integer.parseInt(sc.nextLine());
			if (rep != 1) {
				continueApp = false;
			}

		}
		sc.close();
	}

}
