package fr.iut.uca.service;

import fr.iut.uca.entity.issues.IssueEntity;
import fr.iut.uca.model.issues.Issue;
import fr.iut.uca.qualifier.RepositoryQualifier;
import fr.iut.uca.qualifier.RepositoryType;
import fr.iut.uca.repository.issues.IIssueRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.Optional;

import static fr.iut.uca.extension.issues.IssueExtensions.issueToModel;
import static fr.iut.uca.extension.issues.IssueExtensions.issuesToModels;

@ApplicationScoped
public class IssueService {

    @Inject
    @RepositoryQualifier(RepositoryType.MONGO)
    IIssueRepository issueRepository;


    public List<Issue> getAll(int index,
                              int count) throws IllegalArgumentException {

        List<IssueEntity> issueEntities = issueRepository.getItems(index, count);

        return issuesToModels(issueEntities);
    }

    public Issue getOne(String id)
            throws NotFoundException {

        Optional<IssueEntity> entity = issueRepository.getItemById(id);

        if (entity.isEmpty()) {
            throw new NotFoundException("The issue cannot be found");
        }

        return issueToModel(entity.get());
    }
}
