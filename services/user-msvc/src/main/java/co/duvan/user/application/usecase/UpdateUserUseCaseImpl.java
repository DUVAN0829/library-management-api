package co.duvan.user.application.usecase;

import co.duvan.user.application.ports.input.UpdateUserUseCase;
import co.duvan.user.application.ports.output.UserRepositoryPort;
import co.duvan.user.domain.exceptions.UserNotFoundException;
import co.duvan.user.domain.model.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateUserUseCaseImpl implements UpdateUserUseCase {

    private final UserRepositoryPort repositoryPort;

    @Override
    public User update(Long id, User user) {

        return repositoryPort.findById(id)
                .map(userDb -> {

                    userDb.setFirstname(user.getFirstname());
                    userDb.setLastname(user.getLastname());
                    userDb.setDocumentType(user.getDocumentType());
                    userDb.setDocumentNumber(user.getDocumentNumber());
                    userDb.setBirthdate(user.getBirthdate());
                    userDb.setGender(user.getGender());
                    userDb.setAddress(user.getAddress());
                    userDb.setPhoneNumber(user.getPhoneNumber());
                    userDb.setNationality(user.getNationality());

                    return repositoryPort.save(user);

                }).orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

    }

}
