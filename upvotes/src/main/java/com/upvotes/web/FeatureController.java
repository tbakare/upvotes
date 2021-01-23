package com.upvotes.web;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.upvotes.domain.Comment;
import com.upvotes.domain.Feature;
import com.upvotes.domain.User;
import com.upvotes.service.FeatureService;

@Controller
@RequestMapping("/products/{productId}/features")
public class FeatureController {
	Logger log = LoggerFactory.getLogger(FeatureController.class);
	
	@Autowired
	private FeatureService featureService;
	
	@PostMapping("")
	public String createFeature(@PathVariable Integer productId, @AuthenticationPrincipal User user) {
		Feature feature = featureService.createFeature(productId, user);
		return "redirect:/products/" + productId + "/features/" + feature.getId();
	}
	
	@GetMapping("{featureId}" )
	public String getFeature(@AuthenticationPrincipal User user, @PathVariable Integer productId, @PathVariable Integer featureId, ModelMap model) {
		Optional<Feature> featureOpt = featureService.findById(featureId);
		if (featureOpt.isPresent()) {
			Feature feature = featureOpt.get();
			model.put("feature", feature);
			model.put("comments", getCommentsWithoutDuplicates(feature));
		}
		model.put("user", user);
		return "feature";
	}

	private Set<Comment> getCommentsWithoutDuplicates(Feature feature) {
		
		return feature.getComments();
	} 
	@PostMapping("{featureId}")
	public String updateFeature(@AuthenticationPrincipal User user, Feature feature, @PathVariable Integer productId, @PathVariable Integer featureId) {
		feature.setUser(user);
		feature = featureService.save(feature);
		String encodedProductName;
	 try {
		encodedProductName = URLEncoder.encode( feature.getProduct().getName(), "UTF-8");
	} catch (UnsupportedEncodingException e) {
		log.warn("Unable to encode the URL string: " + feature.getProduct().getName() + ", redirecting to dashboard");
		return "redirect:/dashboard";
	}
	 return "redirect:/p/" + encodedProductName ;
	}
}
