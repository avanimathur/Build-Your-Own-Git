import java.util.*;

public class PlaylistManager {
    private List<String> suggestedTracks = new ArrayList<>();
    private List<String> approvedTracks = new ArrayList<>();

    public void suggestTrack(String trackUri) {
        suggestedTracks.add(trackUri);
        System.out.println("Suggested track: " + trackUri);
    }

    public void reviewSuggestions() {
        Scanner scanner = new Scanner(System.in);
        for (String track : suggestedTracks) {
            System.out.println("Approve track? (yes/no): " + track);
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("yes")) {
                approvedTracks.add(track);
            }
        }
        suggestedTracks.clear();
    }

    public List<String> getApprovedTracks() {
        return approvedTracks;
    }
}
