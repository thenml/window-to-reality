# window-to-reality

Simple mod to modify the Minecraft window to allow transparency. Made for video creators and map makers.

Technically Fabric API is optional, but there are still bugs so I wouldn't recommend removing it yet.

## Features

- Makes the Minecraft game window support transparency.
- Adds four utility blocks with different breakable/passthrough combinations:
  - `cutout` - breakable, solid
  - `cutout_barrier` - unbreakable, solid
  - `cutout_window` - breakable, passthrough
  - `cutout_void` - unbreakable, passthrough
- Clear Sky builtin texturepack:
  - Enables transparent sky rendering by modifying the sky shader and removing fog.

## Version Support

- Active updates are primarily done for the latest version.
- Older versions may receive releases and updates if requested or contributed through community pull requests.

No additional content features are currently planned. NeoForge support not planned.

## Compatibility

- No guarantee that mod custom screenshotting or rendering will work.
- Any texturepacks that modify `core/terrain.fsh` and `post/transparency.fsh` should apply the same fixes, otherwise transparency will break.