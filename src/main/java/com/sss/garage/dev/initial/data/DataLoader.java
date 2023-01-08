package com.sss.garage.dev.initial.data;

import java.util.List;

import com.sss.garage.model.game.Game;
import com.sss.garage.model.game.GameRepository;
import com.sss.garage.model.game.family.GameFamily;
import com.sss.garage.model.game.family.GameFamilyRepository;
import com.sss.garage.model.user.DiscordUser;
import com.sss.garage.model.user.UserRepository;
import com.sss.garage.service.discord.api.DiscordApiService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataLoader {

    private final DiscordApiService discordApiService;

    @Autowired
    public DataLoader(final UserRepository userRepository,
                      final GameFamilyRepository gameFamilyRepository,
                      final GameRepository gameRepository,
                      final DiscordApiService discordApiService) {
        this.discordApiService = discordApiService;
        loadInitialData(userRepository, gameFamilyRepository, gameRepository);
    }

    public void loadInitialData(final UserRepository userRepository, final GameFamilyRepository gameFamilyRepository, GameRepository gameRepository) {
        discordApiService.persistAllRoles();
        userRepository.save(newUser("507654703568519168")); //Rychu Peja solo

        final GameFamily f1 = newGameFamily("F1");
        gameFamilyRepository.save(f1);

        gameRepository.saveAll(List.of(newGame("F122", f1), newGame("F121", f1)));
    }

    private DiscordUser newUser(final String id) {
        final DiscordUser user = new DiscordUser();
        user.setId(id);
        return user;
    }

    private GameFamily newGameFamily(final String name) {
        final GameFamily gameFamily = new GameFamily();
        gameFamily.setName(name);
        return gameFamily;
    }

    private Game newGame(final String name, final GameFamily gameFamily) {
        final Game game = new Game();
        game.setName(name);
        game.setGameFamily(gameFamily);
        return game;
    }
}
