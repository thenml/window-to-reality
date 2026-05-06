# window-to-reality

Simple no-dependency* mod to modify the Minecraft window to allow transparency. Made for video creators and map makers.

(*Fabric API needed for content features)

## Features

- Makes the Minecraft game window support transparency.
- Adds four utility blocks with different breakable/passthrough combinations (with FAPI):
  - `cutout` - breakable, solid
  - `cutout_barrier` - unbreakable, solid
  - `cutout_window` - breakable, passthrough
  - `cutout_void` - unbreakable, passthrough
- Clear Sky builtin texturepack (with FAPI):
  - Enables transparent sky rendering by modifying the sky shader and removing fog.

## Version Support

- A base implementation exists for all supported Minecraft versions.
- Active updates are primarily done for the latest version.
- Older versions may receive updates if requested or contributed through community pull requests.

No additional content features are currently planned. NeoForge support not planned.

## Compatibility

- No guarantee that mod custom screenshotting or rendering will work.
- Any texturepacks that modify `core/terrain.fsh` and `post/transparency.fsh` should apply the same fixes, otherwise transparency will break.