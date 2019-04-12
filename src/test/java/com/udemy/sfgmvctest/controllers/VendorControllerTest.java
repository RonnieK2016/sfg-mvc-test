package com.udemy.sfgmvctest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.udemy.sfgmvctest.api.v1.model.VendorDTO;
import com.udemy.sfgmvctest.exceptions.ResourceNotFoundException;
import com.udemy.sfgmvctest.services.VendorService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class VendorControllerTest {

    private static final String NAME = "Jimmy";
    private static final long ID = 1L;
    private static final String API_URL = "/api/v1/vendors/";
    private static final String API_URL_WITH_ID = API_URL + ID;

    @Mock
    private VendorService VendorService;

    @InjectMocks
    private VendorController VendorController;

    private MockMvc mockMvc;

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(VendorController)
                .setControllerAdvice(new RestExceptionHandler())
                .build();
    }

    @Test
    public void getAllVendors() throws Exception {

        when(VendorService.getAllVendors()).thenReturn(Arrays.asList(new VendorDTO(), new VendorDTO()));

        mockMvc.perform(get(API_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendors", hasSize(2)));
    }

    @Test
    public void getVendorById() throws Exception {
        //given
        VendorDTO vendor = new VendorDTO();
        vendor.setName(NAME);

        when(VendorService.findVendorById(ID)).thenReturn(vendor);

        mockMvc.perform(get(API_URL_WITH_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME)));
    }

    @Test
    public void testCreateVendor() throws Exception {
        //given
        VendorDTO vendor = new VendorDTO();
        vendor.setName(NAME);

        VendorDTO returnVendor = new VendorDTO();
        returnVendor.setName(NAME);
        returnVendor.setVendorUrl(API_URL_WITH_ID);
        returnVendor.setId(ID);

        when(VendorService.saveVendor(vendor)).thenReturn(returnVendor);

        mockMvc.perform(post(API_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(vendor)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo(NAME)))
                .andExpect(jsonPath("$.vendor_url", equalTo(API_URL_WITH_ID)));

    }

    @Test
    public void testUpdateVendor() throws Exception {
        //given
        VendorDTO vendor = new VendorDTO();
        vendor.setName(NAME);

        VendorDTO returnVendor = new VendorDTO();
        returnVendor.setName(NAME);
        returnVendor.setVendorUrl(API_URL_WITH_ID);
        returnVendor.setId(ID);

        when(VendorService.saveVendorById(ID, vendor)).thenReturn(returnVendor);

        mockMvc.perform(put(API_URL_WITH_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(vendor)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME)))
                .andExpect(jsonPath("$.vendor_url", equalTo(API_URL_WITH_ID)));

    }

    @Test
    public void testPatchVendor() throws Exception {

        //given
        VendorDTO vendor = new VendorDTO();
        vendor.setName(NAME);

        VendorDTO returnDTO = new VendorDTO();
        returnDTO.setName(NAME);
        returnDTO.setVendorUrl("/api/v1/vendors/" + ID);

        when(VendorService.patchVendor(ID, vendor)).thenReturn(returnDTO);

        mockMvc.perform(patch(API_URL_WITH_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(vendor)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME)))
                .andExpect(jsonPath("$.vendor_url", equalTo(API_URL_WITH_ID)));
    }

    @Test
    public void testDeleteVendor() throws Exception {

        mockMvc.perform(delete(API_URL_WITH_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(VendorService).deleteVendorById(ID);
    }

    @Test
    public void testNotFoundException() throws Exception {

        when(VendorService.findVendorById(anyLong())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get(API_URL + "/222")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}