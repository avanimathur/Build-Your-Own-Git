// fetch API for album

async function fetchAlbum(token, albumId) {
    const response = await fetch(`https://api.spotify.com/v1/albums/${albumId}`, {
        method: "GET",
        headers: {
            "Authorization": `Bearer ${token}`,
            "Content-Type": "application/json"
        }
    });

    if (!response.ok) {
        throw new Error("Failed to fetch album");
    }

    return await response.json();
}
