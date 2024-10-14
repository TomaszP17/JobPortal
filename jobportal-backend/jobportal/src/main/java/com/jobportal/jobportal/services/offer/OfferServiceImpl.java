package com.jobportal.jobportal.services.offer;

import com.jobportal.jobportal.dtos.company.CompanyResponseDTO;
import com.jobportal.jobportal.dtos.employmenttype.EmploymentTypeResponseDTO;
import com.jobportal.jobportal.dtos.experience.ExperienceResponseDTO;
import com.jobportal.jobportal.dtos.localization.LocalizationResponseDTO;
import com.jobportal.jobportal.dtos.offer.*;
import com.jobportal.jobportal.dtos.technology.TechnologyResponseDTO;
import com.jobportal.jobportal.dtos.worktype.WorkTypeResponseDTO;
import com.jobportal.jobportal.entities.offer.*;
import com.jobportal.jobportal.entities.user.Company;
import com.jobportal.jobportal.exceptions.offer.*;
import com.jobportal.jobportal.exceptions.user.UserDoesNotExistException;
import com.jobportal.jobportal.mappers.LocalizationMapper;
import com.jobportal.jobportal.mappers.OfferMapper;
import com.jobportal.jobportal.mappers.UserMapper;
import com.jobportal.jobportal.repositories.*;
import com.jobportal.jobportal.services.localization.LocalizationService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;
    private final TechnologyRepository technologyRepository;
    private final ExperienceRepository experienceRepository;
    private final WorkTypeRepository workTypeRepository;
    private final EmploymentTypeRepository employmentTypeRepository;
    private final CompanyRepository companyRepository;
    private final OfferMapper offerMapper;
    private final OfferTechnologyRepository offerTechnologyRepository;
    private final OfferExperienceRepository offerExperienceRepository;
    private final OfferEmploymentTypeRepository offerEmploymentTypeRepository;
    private final OfferWorkTypeRepository offerWorkTypeRepository;
    private final LocalizationService localizationService;
    private final LocalizationMapper localizationMapper;
    private final UserMapper userMapper;

    public OfferServiceImpl(OfferRepository offerRepository, TechnologyRepository technologyRepository, ExperienceRepository experienceRepository, WorkTypeRepository workTypeRepository, EmploymentTypeRepository employmentTypeRepository, CompanyRepository companyRepository, OfferMapper offerMapper, OfferTechnologyRepository offerTechnologyRepository, OfferExperienceRepository offerExperienceRepository, OfferEmploymentTypeRepository offerEmploymentTypeRepository, OfferWorkTypeRepository offerWorkTypeRepository, LocalizationService localizationService, LocalizationMapper localizationMapper, UserMapper userMapper) {
        this.offerRepository = offerRepository;
        this.technologyRepository = technologyRepository;
        this.experienceRepository = experienceRepository;
        this.workTypeRepository = workTypeRepository;
        this.employmentTypeRepository = employmentTypeRepository;
        this.companyRepository = companyRepository;
        this.offerMapper = offerMapper;
        this.offerTechnologyRepository = offerTechnologyRepository;
        this.offerExperienceRepository = offerExperienceRepository;
        this.offerEmploymentTypeRepository = offerEmploymentTypeRepository;
        this.offerWorkTypeRepository = offerWorkTypeRepository;
        this.localizationService = localizationService;
        this.localizationMapper = localizationMapper;
        this.userMapper = userMapper;
    }
    @Override
    public List<OfferResponseDTO> getAllOffers(String orderBy, String sortBy) {
        Sort sort = getSort(orderBy, sortBy);
        List<Offer> offers = offerRepository.findAll(sort);
        return offers.stream().map(offerMapper::toOfferResponseFromOffer).toList();
    }

    private Sort getSort(String orderBy, String sortBy) {
        if (sortBy == null || sortBy.isEmpty()) {
            sortBy = "Latest";
        }
        if (orderBy == null || orderBy.isEmpty()) {
            orderBy = "desc";
        }
        Map<String, String> sortFields = Map.of(
                "Latest", "createdAt",
                "Highest Salary", "salaryMax",
                "Lowest Salary", "salaryMin"
        );
        String sortField = sortFields.get(sortBy);
        if (sortField == null) {
            throw new IllegalArgumentException("Invalid sortBy parameter: " + sortBy);
        }
        Sort.Direction direction = "desc".equalsIgnoreCase(orderBy) ? Sort.Direction.DESC : Sort.Direction.ASC;
        return Sort.by(direction, sortField);
    }

    @Override
    public OfferDetailsResponseDTO getOffer(long offerId) {
        Offer offer = offerRepository
                .findById(offerId)
                .orElseThrow(() -> new OfferDoesNotExistsException("Offer does not exists"));

        OfferResponseDTO offerResponseFromOffer = offerMapper.toOfferResponseFromOffer(offer);

        List<OfferTechnology> offerTechnologyList = offerTechnologyRepository.findAllByOfferId(offerId);
        List<TechnologyResponseDTO> technologyResponseDTOList = new ArrayList<>();
        offerTechnologyList.forEach(
                offerTechnology -> technologyResponseDTOList
                        .add(offerMapper.toTechnologyResponseDTOFromTechnology(offerTechnology.getTechnology())));

        List<OfferWorkType> offerWorkTypeList = offerWorkTypeRepository.findAllByOfferId(offerId);
        List<WorkTypeResponseDTO> workTypeResponseDTOList = new ArrayList<>();
        offerWorkTypeList.forEach(
                offerWorkType -> workTypeResponseDTOList
                        .add(offerMapper.toWorkTypeResponseDTOFromWorkType(offerWorkType.getWorkType()))
        );

        List<OfferExperience> offerExperienceList = offerExperienceRepository.findAllByOfferId(offerId);
        List<ExperienceResponseDTO> experienceResponseDTOList = new ArrayList<>();
        offerExperienceList.forEach(
                offerExperience -> experienceResponseDTOList
                        .add(offerMapper.toExperienceResponseDTOFromExperience(offerExperience.getExperience()))
        );

        List<OfferEmploymentType> offerEmploymentTypeList = offerEmploymentTypeRepository.findAllByOfferId(offerId);
        List<EmploymentTypeResponseDTO> employmentTypeResponseDTOList = new ArrayList<>();
        offerEmploymentTypeList.forEach(
                offerEmploymentType -> employmentTypeResponseDTOList
                        .add(offerMapper.toEmploymentTypeResponseDTOFromEmploymentType(offerEmploymentType.getEmploymentType()))
        );

        LocalizationResponseDTO localizationResponseDTOFromLocalization = localizationMapper.toLocalizationResponseDTOFromLocalization(offer.getLocalization());

        CompanyResponseDTO responseFromCompany = userMapper.toResponseFromCompany(offer.getCompany());

        return new OfferDetailsResponseDTO(
                offerResponseFromOffer,
                technologyResponseDTOList,
                workTypeResponseDTOList,
                experienceResponseDTOList,
                employmentTypeResponseDTOList,
                localizationResponseDTOFromLocalization,
                responseFromCompany
        );
    }

    @Override
    @Transactional
    public void addOffer(OfferCreateRequestDTO requestDTO) {
        if(requestDTO.getSalaryMin() > requestDTO.getSalaryMax()){
            throw new InvalidOfferSalaryException("SalaryMin is bigger than SalaryMax in that offer");
        }

        List<Technology> technologies = requestDTO.getTechnologies()
                .stream()
                .map(technologyId -> technologyRepository.findById(technologyId)
                        .orElseThrow(() -> new TechnologyDoesNotExistsException("Technology with id: " + technologyId + " does not exist")))
                .toList();

        List<EmploymentType> employmentTypes = requestDTO.getEmploymentType()
                .stream()
                .map(employmentTypeId -> employmentTypeRepository.findById(employmentTypeId)
                        .orElseThrow(() -> new EmploymentTypeDoesNotExistsException("EmploymentType with that id: " + employmentTypeId + " does not exists"))
                ).toList();
        List<Experience> experiences = requestDTO.getExperiences()
                .stream()
                .map(experienceId -> experienceRepository
                        .findById(experienceId)
                        .orElseThrow(() -> new ExperienceDoesNotExistsException("Experience with that id: " + experienceId + " does not exists"))
                ).toList();
        List<WorkType> workTypes = requestDTO.getWorkTypes()
                .stream()
                .map(workTypeId -> workTypeRepository
                        .findById(workTypeId)
                        .orElseThrow(() -> new WorkTypeDoesNotExistsException("WorkType with that id: " + workTypeId + " does not exists"))
                ).toList();
        Company company = companyRepository
                .findById(requestDTO.getCompanyId())
                .orElseThrow(() -> new UserDoesNotExistException("Company with that id: " + requestDTO.getCompanyId() + " does not exists")
                );

        Offer offer = offerMapper.toOfferFromCreateRequest(requestDTO);
        offer.setCompany(company);
        offer.setLocalization(localizationService.addLocalization(requestDTO.getRequestDTO()));

        offerRepository.save(offer);

        technologies.forEach(
                technology -> {
                    OfferTechnology offerTechnology = OfferTechnology.builder()
                            .offer(offer)
                            .technology(technology)
                            .build();
                    offerTechnologyRepository.save(offerTechnology);
                });

        experiences.forEach(
                experience -> {
                    OfferExperience offerExperience = OfferExperience.builder()
                            .offer(offer)
                            .experience(experience)
                            .build();
                    offerExperienceRepository.save(offerExperience);
                }
        );

        employmentTypes.forEach(
                employmentType -> {
                    OfferEmploymentType offerEmploymentType = OfferEmploymentType.builder()
                            .offer(offer)
                            .employmentType(employmentType)
                            .build();
                    offerEmploymentTypeRepository.save(offerEmploymentType);
                }
        );

        workTypes.forEach(
                workType -> {
                    OfferWorkType offerWorkType = OfferWorkType.builder()
                            .offer(offer)
                            .workType(workType)
                            .build();
                    offerWorkTypeRepository.save(offerWorkType);
                }
        );
    }

    @Override
    @Transactional
    public void deleteOffer(long offerId) {
        offerRepository
                .findById(offerId)
                .orElseThrow(() -> new OfferDoesNotExistsException("Offer with id: " + offerId + " does not exists"));
        offerRepository.deleteById(offerId);
    }

    @Override
    public Page<NextOfferResponseDTO> getNextOffers(Pageable pageable) {

        List<NextOfferResponseDTO> nextOfferResponseDTOList = new ArrayList<>();
        Page<Offer> all = offerRepository.findAll(pageable);

        all.forEach(offer -> {
            LocalDateTime createdAt = offer.getCreatedAt();
            LocalDateTime now = LocalDateTime.now();
            int between = (int) ChronoUnit.DAYS.between(createdAt, now);

            List<OfferTechnology> allByOfferId = offerTechnologyRepository.findAllByOfferId(offer.getId());
            List<String> technologiesNames = new ArrayList<>();
            allByOfferId.forEach(offerTechnology -> technologiesNames.add(offerTechnology.getTechnology().getName()));

            String name = offer.getCompany().getName();

            NextOfferResponseDTO dto = new NextOfferResponseDTO(
                    offer.getId(),
                    offer.getTitle(),
                    between,
                    offer.getSalaryMin(),
                    offer.getSalaryMax(),
                    name,
                    technologiesNames
            );
            nextOfferResponseDTOList.add(dto);
        });
        return new PageImpl<>(nextOfferResponseDTOList, pageable, all.getTotalElements());
    }

    @Override
    public OfferMarkerResponseDTO getMarkedOffer(long offerId) {
        Offer offer = offerRepository
                .findById(offerId)
                .orElseThrow(() -> new OfferDoesNotExistsException("Offer does not exists"));
        return offerMapper.toOfferMarkerResponseFromOffer(offer);
    }
}
