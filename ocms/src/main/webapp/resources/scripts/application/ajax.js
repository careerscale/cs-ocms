
	$(document)
			.ready(
					function() {
						$.get("/master/states", function(data, status) {
							$.each(data, function(key, val) {

							});
						});

						$('select#countryId')
								.change(
										function() {

											$
													.get(
															"/master/states?countryId="
																	+ $(
																			'#countryId')
																			.val(),
															function(data,
																	status) {

																var html = '<option value="">Select your State</option>';

																jQuery(
																		'#dropdown')
																		.append(
																				html);
																$
																		.each(
																				data,
																				function(
																						id,
																						value) {
																					html += '<option value="'+value.id+'">'
																							+ value.name
																							+ '</option>';

																				}

																		);

																$(
																		'select#stateId')
																		.empty()
																		.append(
																				html);
															});
										});

						$('select#stateId')
								.change(
										function() {

											$
													.get(
															"/master/cities?stateId="
																	+ $(
																			'#stateId')
																			.val(),
															function(data,
																	status) {

																var html = '<option value="">Select your City</option>';

																jQuery(
																		'#dropdown')
																		.append(
																				html);
																$
																		.each(
																				data,
																				function(
																						key,
																						value) {

																					html += '<option value="'+value.id+'">'
																							+ value.name
																							+ '</option>';

																				}

																		);

																$(
																		'select#cityId')
																		.empty()
																		.append(
																				html);
															});
										});

					});

