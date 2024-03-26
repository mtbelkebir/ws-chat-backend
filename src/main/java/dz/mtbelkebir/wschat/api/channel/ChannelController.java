package dz.mtbelkebir.wschat.api.channel;

import dz.mtbelkebir.wschat.api.exception.ChannelNotFoundException;
import org.springframework.web.bind.annotation.*;


import dz.mtbelkebir.wschat.api.util.GenericResponse;
import lombok.RequiredArgsConstructor;




import org.springframework.http.ResponseEntity;


@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/v1/channels")
public class ChannelController {

    private final ChannelService channelService;
    
    @PostMapping
    public ResponseEntity<?> createChannel(@RequestBody NewChannelDTO request) {
        ChannelDTO c = channelService.createNewChannel(request);

        return ResponseEntity.ok().body(GenericResponse.builder().message("Channel created successfully !").data(c).build());
    }
    
    @GetMapping("/{id}/join")
    public ResponseEntity<?> joinChannel(@PathVariable Long id) throws ChannelNotFoundException {
        channelService.joinChannel(id);

        return ResponseEntity
                .ok()
                .body(GenericResponse.builder().success(true).message("You successfully joined the channel").build());
    }

    @ExceptionHandler(ChannelNotFoundException.class)
    public ResponseEntity<?> channelNotFound() {
        return ResponseEntity
                .status(404)
                .body(GenericResponse.builder().message("The requested channel was not found").success(false).build());
    }
}
